package com.example.kafkaproducer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class KafkaController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/send")
    public String sendMessage() {
        // KafkaTemplate 사용해서 메시지 보내기
        kafkaTemplate.send("my-topic","test");
        return "Message Sent";
    }
}
