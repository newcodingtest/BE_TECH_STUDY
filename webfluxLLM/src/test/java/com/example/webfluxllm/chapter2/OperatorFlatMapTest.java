package com.example.webfluxllm.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class OperatorFlatMapTest {
    /*
      Mono<Mono<T>> -> Mono<T>
      Mono<Flux<T>> -> Flux<T>
      Flux<Mono<T>> -> Flux<T>

      위와 같이 비동기가 겹쳐진 구조를 비동기 1개로 평탄화 시켜주는것이 flatMap이다.
     * */

    @Test
    public void monoToFlux(){
        Mono<Integer> one = Mono.just(1);

        Flux<Integer> integerFlux = one.flatMapMany(data -> {
            return Flux.just(data, data+1, data+2);
        });
        integerFlux.subscribe(data -> System.out.println("data = " + data));
    }

    @DisplayName("flatMap 사용 2가지 방법")
    @Test
    public void testWebClientFlatMap(){
        /**
         * flatMap은 처리 속도가 빠른 순서대로 방출한다.(입력순서가 아니다.)
         * 순서 보장을 원하면 flatMapSequential을 사용하자
         * */
        Flux<String> flatMap = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
                callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                callWebClient("3단계 - 최종 응답", 500))
                .flatMap(monoData -> {
                    return monoData;
                });

        flatMap.subscribe(data -> System.out.println("flatMap data = " + data));

        Flux<String> flatMapSequential = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                        callWebClient("3단계 - 최종 응답", 500))
                .flatMapSequential(monoData -> {
                    return monoData;
                });

        flatMapSequential.subscribe(data -> System.out.println("flatMapSequential data = " + data));


//        Flux<String> flatMapSequential = Flux.just(callWebClient("1단계 - 문제 이해하기", 1500),
//                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
//                        callWebClient("3단계 - 최종 응답", 500))
//                .flatMapSequential(monoData -> {
//                    return monoData;
//                });

        //위 주석과 같이 flatMap에서 어느 변환작업도 사용하지 않는다면 merge를 사용해서 좀 더 깔끔하게 코딩 가능
        //concat, concatMap은 내부에 로직 호출 순서대로 실행하고 끝날때가지 쓰레드가 대기하고 다음 호출을 또 실행하는 식으로 진행되기
        //때문에 비효율적이다. => 사용하지 말자
        Flux<String> merge = Flux.mergeSequential(callWebClient("1단계 - 문제 이해하기", 1500),
                        callWebClient("2단계 - 문제 단계별로 풀어가기", 1000),
                        callWebClient("3단계 - 최종 응답", 500));

        merge.subscribe(data -> {
            System.out.println("merge data = " + data);
        });


        Flux<String> objectFlux = Flux.<Mono<String>>create(sink -> {
            sink.next(callWebClient("1단계 - 문제 이해하기", 1500));
            sink.next(callWebClient("2단계 - 문제 단계별로 풀어가기", 1000));
            sink.next(callWebClient("3단계 - 최종 응답", 500));
            sink.complete();
        }).flatMap(monoData -> {
            return monoData;
        });



        try {
            Thread.sleep(10000);
        }catch (Exception e){
        }
    }

    public Mono<String> callWebClient(String request, long delay){
        return Mono.defer(() -> {
            try {
                Thread.sleep(delay);
                return Mono.just(request+ " -> 딜레이: " + delay);

            } catch (Exception e) {
                return Mono.empty();
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
