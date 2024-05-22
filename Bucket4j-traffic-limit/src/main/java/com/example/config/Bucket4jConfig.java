package com.example.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@ConditionalOnProperty(prefix = "token", name = "policy", havingValue = "all")
@Configuration
public class Bucket4jConfig {

    //5개의 토큰이 존재하며, 1분에 1개씩 리필이 된다.
    private Refill refill(){
        return Refill.intervally(5, Duration.ofMinutes(1));
    }

    private Bandwidth limit(){
        return Bandwidth.classic(5, refill());
    }

    @Bean
    public Bucket bucket(){
        return Bucket.builder()
                .addLimit(limit())
                .build();
    }




}
