package com.example.rabbitmqperformance.service;

import com.example.rabbitmqperformance.dto.BenchmarkMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Component
public class PerformanceProducer implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final CachingConnectionFactory cachingConnectionFactory;


    @Override
    public void run(String... args) throws InterruptedException {
        int total = 91900000;
        String data = "X".repeat(1024); // 2KB
        int threadPoolSize = 1;

        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        AtomicInteger sentCount = new AtomicInteger(0);
        long start = System.currentTimeMillis();

        // TPS 출력용 스레드
        Thread tpsPrinter = new Thread(() -> {
            int prevCount = 0;
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
                int currentCount = sentCount.get();
                int tps = currentCount - prevCount;
                prevCount = currentCount;
                System.out.printf("Current TPS: %d messages/sec\n", tps);
            }
        });
        tpsPrinter.start();

        for (int i = 0; i < total; i++) {
            executor.submit(() -> {
                BenchmarkMessage msg = new BenchmarkMessage(data, System.currentTimeMillis());
                rabbitTemplate.convertAndSend("test", msg); // exchange와 routing key는 설정에 맞게 변경
                //getTemplate().convertAndSend("test", msg);
                sentCount.incrementAndGet();
            });
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(10, TimeUnit.MINUTES);

        tpsPrinter.interrupt();
        tpsPrinter.join();

        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("Sent %d messages in %dms%n", total, elapsed);
    }



    // ThreadLocal로 각 스레드에 하나씩 RabbitTemplate 제공
//    private final ThreadLocal<RabbitTemplate> threadLocalTemplate = ThreadLocal.withInitial(() -> {
//        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
//        template.setChannelTransacted(false); // 성능 고려: 필요 시 true
//        return template;
//    });
//
//    public RabbitTemplate getTemplate() {
//        return threadLocalTemplate.get();
//    }
}