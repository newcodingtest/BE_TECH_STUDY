package com.example.controller;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@ConditionalOnProperty(prefix = "token", name = "policy", havingValue = "request")
@Slf4j
@RequestMapping("/api")
@Controller
public class Bucket4jRequestControllerV2 {

    private Bucket bucket;

    public Bucket4jRequestControllerV2(){
        Refill refill = Refill.intervally(5, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(5, refill);

        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @GetMapping("/dataV2")
    public ResponseEntity<Integer> getApiV2(){
        if (bucket.tryConsume(1)){
            log.info("잔여 토큰 {} ", bucket.getAvailableTokens());
            return new ResponseEntity<>(atomicInteger.getAndIncrement(), null, HttpStatus.OK);
        }
        return new ResponseEntity<>(atomicInteger.get(), null, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
    }



}
