package com.example.webfluxllm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * 그냥 테스트 클래스 입니다.
 * 
 * @author 윤주영
 *
 * */
@Slf4j
@RestController
@RequestMapping("/reactive")
public class ExampleController {

    /**
     * 테스트용 메서드 입니다.
     *
     * @param  test1 테스트 파라미터1
     * @param  test2 테스트 파라미터2
     * @return 빈값
     * @throws IllegalArgumentException 테스트 예외 발생
     * */
    public String test(String test1, String test2){
        return "";
    }


    /**
     * tomcat 방식
     * */
    @GetMapping("/list/legacy")
    public List<Integer> produceOneToNineLegacy(){
        List<Integer> sink = new ArrayList<>();
            for (int i=1; i<=9; i++){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                sink.add(i);
            }
            return sink;
    }
    /**
    * tomcat 방식을 fromCallable 적용해서 마이그레이션
    * */
    @GetMapping("/list/legacy")
    public Mono<List<Integer>> produceOneToNineLegacyV1(){
        return Mono.fromCallable(() -> {
            List<Integer> sink = new ArrayList<>();
            for (int i=1; i<=9; i++){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                sink.add(i);
            }
            return sink;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * tomcat 방식을  defer 적용해서 마이그레이션
     * */
    @GetMapping("/list/legacy")
    public Mono<List<Integer>> produceOneToNineLegacyV2(){
        return Mono.defer(() -> {
            List<Integer> sink = new ArrayList<>();
            for (int i=1; i<=9; i++){
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                }
                sink.add(i);
            }
            return Mono.just(sink);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/list")
    public Mono<List<Integer>> produceOneToNine(){
        List<Integer> sink = new ArrayList<>();
        for (int i=1; i<=9; i++){
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
            sink.add(i);
        }
        return Mono.just(sink);
    }
    @GetMapping("/list/flux")
    public Flux<Integer> produceOneToNineFlux(){
        return Flux.<Integer>create(sink -> {
            for (int i=1; i<=9; i++){
                try {
                    log.info("현재 처리하고 있는 스레드 이름 : {}", Thread.currentThread().getName());

                    Thread.sleep(500);
                } catch (Exception e) {
                }
                sink.next(i);
            }
            sink.complete();
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
