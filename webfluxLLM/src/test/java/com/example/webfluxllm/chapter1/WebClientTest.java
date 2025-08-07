package com.example.webfluxllm.chapter1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class WebClientTest {


    private WebClient webClient = WebClient.builder().build();

    @Test
    void testWebClient(){
        //비동기 논블럭하게 동작(os 스레드에게 작업을 위임하기 때문)
        Flux<Integer> intFlux = webClient.get()
                .uri("http://localhost:8080/reactive/flux")
                        .retrieve()  // os에게 응답 수신을 위한 os 스레드 대기
                                .bodyToFlux(Integer.class);

        //webclient의 작업이 끝나면
        intFlux.subscribe(data -> {
            System.out.println("현재 쓰레드 이름: " + Thread.currentThread().getName());
            System.out.println("webFlux 구독 중: " + data);
        });
        System.out.println("Netty 이벤트 루프로 스레드 복귀: " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        }catch (Exception e){
        }
    }
}
