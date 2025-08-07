package com.example.webfluxllm.chapter2;


import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BasicFluxOperatorTest {

    /**
     * Flux 시작
     * 데이터: just, empty, from-시리즈,
     * 함수: defer, create
     * */
    @Test
    public void testFluxFromData(){
        Flux.just(1,2,3,4)
                .subscribe(data -> System.out.println("data = " + data));

        //java 이터러블을 상속하는 모든 클래스를 담을 수 있음.
        Flux.fromIterable(List.of(1,2,3,4,5))
                .subscribe(data -> System.out.println("data = " + data));
    }

    /**
         * defer -> 안에서 Flux 객체를 반환해야 합니다.
         * create -> 안에서 동기적인 객체(next)를 반환해야 합니다.
         * */
    @Test
    public void testFluxFromFunction(){
        Flux.defer(() -> {
            return Flux.just(1,2,3,4);
        }).subscribe(data -> System.out.println("data from defer = " + data));

        Flux.create(sink -> {
            sink.next(1);
            sink.next(2);
            sink.next(3);
            sink.complete(); //sink 사용할 때 마지막에 호출
        }).subscribe(data -> System.out.println("data from sink= " + data));
    }

    @Test
    public void testSinkDetail(){
        Flux.<String>create(sink -> {
            AtomicInteger counter = new AtomicInteger(0);
            recursiveFunction(sink);
        })
                .contextWrite(Context.of("counter", new AtomicInteger(0)))
                .subscribe(data -> System.out.println("data = " + data));
    }

    /**
     * Mono -> Flux 변환 => flatMapMany 사용
     * Flux -> Mono 변환 => collectlist 사용
     * */
    @Test
    void testFluxCollectList(){
        Mono<List<Integer>> listMono =  Flux.<Integer>just(1,2,3,4,5)
                .map(data -> data*2)
                .filter(data -> data%4 == 0)
                .collectList();
        
        listMono.subscribe(data -> System.out.println("data = " + data));
    }

    public void recursiveFunction(FluxSink<String> sink){
        AtomicInteger counter = sink.contextView().get("counter");
        if (counter.incrementAndGet() < 10){
            sink.next("sink count" + counter);
            recursiveFunction(sink);
        } else {
            sink.complete();
        }
    }
}
