package com.example.kafkaproducer.config;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.51.152:9092");
        KafkaAdmin kafkaAdmin = new KafkaAdmin(configs);
        kafkaAdmin.setAutoCreate(true); // 명시적으로 true 설정
        return kafkaAdmin;
    }

    @Bean
    public NewTopic exampleTopic() {
        return new NewTopic("my-topic", 3, (short) 1);  // 파티션 3개, 복제 1개
    }

    @Bean
    public ApplicationRunner manualAdminTest() {
        return args -> {
            try (AdminClient adminClient = AdminClient.create(Map.of(
                    AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.51.152:9092"
            ))) {
                NewTopic topic = new NewTopic("manual-topic", 1, (short) 1);
                adminClient.createTopics(List.of(topic)).all().get();
                System.out.println("✅ 수동 토픽 생성 성공");
            } catch (Exception e) {
                System.err.println("❌ 수동 토픽 생성 실패: " + e.getMessage());
            }
        };
    }
}
