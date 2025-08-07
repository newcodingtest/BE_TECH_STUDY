package com.example.webfluxllm.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMonoErrorAndSignalTest {

    //onNext
    @Test
    public void testBasicSignal(){
        Flux.just(1,2,3,4)
                .doOnNext(publishedData -> System.out.println("publishedData = " + publishedData))
                .doOnComplete(() -> System.out.println("스트림이 끝났습니다"))
                .doOnError(ex -> {
                    System.out.println("ex 발생: " + ex);
                })
                .subscribe(data -> System.out.println("data = " + data));
    }


    @Test
    public void testFluxMonoError(){
        Flux.just(1,2,3,4)
                .map(data -> {
                    if (data == 3){
                        throw new RuntimeException();
                    }
                    return data*2;
                })
                .onErrorMap(ex -> new IllegalArgumentException()) //커스텀 한 에러로 치환하고 싶을때
                .onErrorReturn(999) //에러가 났을때 방출되기 원하는 값
                .onErrorComplete() // 에러가 났을때 에러 시그널을 전파하지 않고 complete 시그널을 전파
                .subscribe(data -> System.out.println("data = " + data));
    }

    @Test
    public void testFluxMonoDotError(){
        Flux.just(1,2,3,4)
                .map(data -> {
                    if (data == 3){
                       return Mono.error(new RuntimeException());
                    }
                    return Mono.just(data);
                })
                .subscribe(data -> System.out.println("data = " + data));
    }
}
