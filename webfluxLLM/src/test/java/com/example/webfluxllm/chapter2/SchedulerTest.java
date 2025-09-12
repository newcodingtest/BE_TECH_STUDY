package com.example.webfluxllm.chapter2;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class SchedulerTest {

    /*
    * subscribeOn, publishOn 스레드 할당
    * */
    @Test
    public void testBasic(){
        Mono.<Integer>just(2)
                .map(data -> {
                    System.out.println("map Thread name: " + Thread.currentThread().getName());
                    return data*2;
                })
                .publishOn(Schedulers.parallel())
                .filter(data -> {
                    System.out.println("filter Thread name: " + Thread.currentThread().getName());
                    return data%4==0;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(data -> System.out.println(Thread.currentThread().getName()+" data = " + data));
    }
}
