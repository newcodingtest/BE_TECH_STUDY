package com.example.controller;

import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicInteger;

@ConditionalOnProperty(prefix = "token", name = "policy", havingValue = "all")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class Bucket4jAllController {

    private final Bucket bucket;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/data")
    public ResponseEntity<Integer> getApi(){
        if (bucket.tryConsume(1)){
            log.info("잔여 토큰 {} ", bucket.getAvailableTokens());
            return new ResponseEntity<>(atomicInteger.getAndIncrement(), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(atomicInteger.get(), null, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }

}
