package com.example.rabbitmqperformance.service;

import com.example.rabbitmqperformance.dto.BenchmarkMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@Component
public class PerformanceConsumer {

    private final AtomicInteger count = new AtomicInteger();
    private final LongAdder totalLatency = new LongAdder();
    private final long start = System.currentTimeMillis();

//    @RabbitListener(queues = "test", concurrency = "1")
//    public void receive(BenchmarkMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG)long deliveryTag) throws IOException {
////        try {
////            // 메시지 처리
////            int received = count.incrementAndGet();
////            if (received % 10000 == 0) {
////                long elapsed = System.currentTimeMillis() - start;
////                double tps = received / (elapsed / 1000.0);
////                double avgLatency = totalLatency.doubleValue() / received;
////
////                System.out.printf("Received: %,d, TPS: %.2f, Avg Latency: %.2fms%n",
////                        received, tps, avgLatency);
////            }
////            channel.basicAck(deliveryTag,false);
////        } catch (Exception e) {
////            channel.basicNack(deliveryTag,false,false);
////        }
//
//
//        int received = count.incrementAndGet();
//        if (received % 10000 == 0) {
//            long elapsed = System.currentTimeMillis() - start;
//            double tps = received / (elapsed / 1000.0);
//            double avgLatency = totalLatency.doubleValue() / received;
//
//            System.out.printf("Received: %,d, TPS: %.2f, Avg Latency: %.2fms%n",
//                    received, tps, avgLatency);
//        }
//    }
}