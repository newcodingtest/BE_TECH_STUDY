package com.example.webfluxllm.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class BasicMonoOperatorTest {

    @DisplayName("데이터로 시작하는 Mono(just ,empty)")
    //just, empty
    @Test
    public void startMonoFromData(){
        Mono.just(1).subscribe(data -> System.out.println("data = " + data));
        
        //ex) 사소한 에러가 발생했을 때 로그를 남기고 empty의 Mono를 전파
        Mono.empty().subscribe(data -> System.out.println("data = " + data));
    }

    @DisplayName("함수로 시작하는 Mono(fromCallable -> 동기적인 객체를 반환할 때, " +
            "defer -> Mono를 반환할 때)")
    @Test
    public void startMonoFromFunction(){
        /**
         * restTemplate, JPA -> 블로킹이 발생하는 라이브러리 Mono로 스레드 분리하여 논블로킹으로 처리 가능
         * */
        Mono<String> monoFromCallable = Mono.fromCallable(() -> {
            return callRestTemplate("안녕");
        }).subscribeOn(Schedulers.boundedElastic());


        /**
         * just는 쓰레드가 "안녕" 이라는 로직까지 바로 읽지만 defer는 구독이 이뤄질때 실제 로직을 읽는다.
         * */
        Mono<String> monoFromdefer = Mono.defer(() -> {
            return callWebClient("안녕");
        });
        monoFromdefer.subscribe();

        Mono<String> monoFromJust = Mono.just("안녕");

    }

    @Test
    public void testDeferNecessity(){
        //abc를 만드는 로직도 Mono의 흐름 속에서 관리하고 싶다.
        //ex) b에서 블로킹이 발생하면 subscribeOn을 통해서 논블록하게도 동작가능
        Mono.defer(() -> {
            String a = "안녕";
            String b = "하세";
            String c = "요";
            return callWebClient(a+b+c);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @DisplayName("Mono에서 데이터 방출 개수가 많아져서 Flux 로 바꾸고 싶을때 -> flatMapMany")
    @Test
    public void monoToFlux(){
        Mono<Integer> one = Mono.just(1);
        Flux<Integer> integerFlux = one.flatMapMany(data -> {
            return Flux.just(data ,data+1, data+2);
        });
        integerFlux.subscribe(data -> System.out.println("data = " + data));
    }


    public Mono<String> callWebClient(String request){
        return Mono.just(request + "callRestTemplate 응답");
    }

    public String callRestTemplate(String request){
        return request + "callRestTemplate 응답";
    }
}
