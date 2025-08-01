package com.example.rabbitmqperformance.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

//    @Bean
//    public CachingConnectionFactory cachingConnectionFactory() {
//        CachingConnectionFactory factory = new CachingConnectionFactory("localhost");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setVirtualHost("/");
//
//        factory.setChannelCacheSize(25); // 스레드 수만큼 or 그 이상
//        factory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
//
//        return factory;
//    }


}