package com.example.webfluxllm.chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BasicFluxMonoTest {

    @Test
    public void testBasicFluxMono(){
        Flux.<Integer>just(1,2,3,4,5) //첫 번째는 빈 함수부터, 두 번째는 데이터(just)로부터 시작가능
                .map(data -> data*2)
                .filter(data -> data%4==0)
                .subscribe(data -> System.out.println("flux data = " + data));

        Mono.<Integer>just(2) //첫 번째는 빈 함수부터, 두 번째는 데이터(just)로부터 시작가능
                .map(data -> data*2)
                .filter(data -> data%4==0)
                .subscribe(data -> System.out.println("mono data = " + data));
    }

    @DisplayName("모노 String을 단순 String으로 변환하는 방법")
    @Test
    public void testFluxMonoBlock(){
        Mono<String> justString = Mono.just("String");
        String str = justString.block(); //이렇게 변경할 수 있지만, 왠만하면 사용하지 말자
        System.out.println("str = " + str);
    }
}
