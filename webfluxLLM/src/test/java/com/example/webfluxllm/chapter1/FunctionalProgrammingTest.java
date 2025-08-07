package com.example.webfluxllm.chapter1;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

@SpringBootTest
public class FunctionalProgrammingTest {

    @DisplayName("명령문")
    @Test
    void OneToNineV1(){
        List<Integer> sink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            sink.add(i);
        }

        List<Integer> newSink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            newSink.add(sink.get(i)*2);
        }

        sink = newSink;

        List<Integer> newSink2 = new ArrayList<>();
        for (int i=0; i<=9; i++){
            if (sink.get(i)%4==0){
                newSink2.add(sink.get(i));
            }
        }

        sink = newSink;

        for (int i=0; i<=9; i++){
            System.out.println(sink.get(i));
        }
    }

    @DisplayName("명령문, 메서드 추출")
    @Test
    void OneToNineV2(){
        List<Integer> sink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            sink.add(i);
        }

        sink = map(sink);
        sink = filter(sink);
        forEach(sink);
    }

    private static void forEach(List<Integer> sink) {
        for (int i=0; i<=9; i++){
            System.out.println(sink.get(i));
        }
    }

    private static List<Integer> filter(List<Integer> sink) {
        List<Integer> newSink2 = new ArrayList<>();
        for (int i=0; i<=9; i++){
            if (sink.get(i)%4==0){
                newSink2.add(sink.get(i));
            }
        }
        return newSink2;
    }

    private static List<Integer> map(List<Integer> sink) {
        List<Integer> newSink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            newSink.add(sink.get(i)*2);
        }
        return newSink;
    }

    @DisplayName("명령문, 메서드 추출, Functional api 사용해보기")
    @Test
    void OneToNineV3(){
        List<Integer> sink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            sink.add(i);
        }

        sink = mapV2(sink, data -> data*2);
        sink = filterV2(sink, data -> data%4 == 0);
        forEachV2(sink, data -> System.out.println(data));
    }

    private void forEachV2(List<Integer> sink, Consumer<Integer> consumer) {
        for (int i=0; i<=9; i++){
            consumer.accept(sink.get(i));
        }
    }

    private List<Integer> filterV2(List<Integer> sink, Function<Integer, Boolean> predicate) {
        List<Integer> newSink2 = new ArrayList<>();
        for (int i=0; i<=9; i++){
            if (predicate.apply(sink.get(i))){
                newSink2.add(sink.get(i));
            }
        }
        return newSink2;
    }

    private List<Integer> mapV2(List<Integer> sink, Function<Integer, Integer> mapper) {
        List<Integer> newSink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            newSink.add(mapper.apply(sink.get(i)));
        }
        return newSink;
    }


    @DisplayName("Stream 사용해서 좀 더 깔끔하게")
    @Test
    void OneToNineV4(){
        List<Integer> sink = new ArrayList<>();
        for (int i=0; i<=9; i++){
            sink.add(i);
        }

        IntStream.rangeClosed(1,9).boxed()
                .map(data -> data*2)
                .filter(data -> data%4 == 0)
                .forEach(data -> System.out.println(data));
    }

    @DisplayName("FLUX로 전환")
    @Test
    void OneToNineV5(){
        Flux<Integer> intFlux = Flux.create(sink -> {
            for (int i=1; i<=9; i++){
                sink.next(i);
            }

            sink.complete();
        });

        intFlux.subscribe(data -> System.out.println("webFlux 구독 중: " + data));
    }

    @DisplayName("오퍼레이터 사용해서 FLUX코드 깔끔하게")
    @Test
    void OneToNineV6(){
        Flux.fromIterable(IntStream.rangeClosed(1,9).boxed().toList())
                .map(data -> data*2)
                .filter(data -> data%4 == 0)
                .subscribe(data -> System.out.println(data));
    }
}
