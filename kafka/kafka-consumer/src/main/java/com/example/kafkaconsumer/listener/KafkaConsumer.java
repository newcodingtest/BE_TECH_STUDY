package com.example.kafkaconsumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my-topic", groupId = "yoon-1")
    public void listenWithGroup1(String message) {
        log.info("yoon group 1 : {}", message);
    }
}
