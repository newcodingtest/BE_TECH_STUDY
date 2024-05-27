package com.example.rabbitmqclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class HttpService {

    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081/test")
            .build();


    public Mono<HttpStatus> apiPostCallAsync(String msg, String url) {
        return webClient.post()
                .uri(url)
                .bodyValue(msg)
                .retrieve()
                .toEntity(String.class)
                .map(responseEntity -> responseEntity.getStatusCode());
    }
}

