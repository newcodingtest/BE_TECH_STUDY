package com.example.httpserver.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequestMapping("/test")
@Controller
public class TestApi {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @PostMapping("/api/v1")
    public ResponseEntity receiveV1(@RequestBody String msg){

        log.info(Thread.currentThread().getName()+" v1 ::: {} ",  atomicInteger.getAndIncrement());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity(msg, HttpStatus.OK);
    }

    @PostMapping("/api/v2")
    public ResponseEntity receiveV2(@RequestBody String msg){

        log.info(Thread.currentThread().getName()+" v2 ::: {} ",  atomicInteger.getAndIncrement());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity(msg, HttpStatus.OK);
    }
}
