package com.example.activemqperformance.service;

import com.example.activemqperformance.dto.BenchmarkMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class PerformanceProducer implements CommandLineRunner {

    private final JmsTemplate jmsTemplate;

    public PerformanceProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void run(String... args) throws InterruptedException {
        int total = 410000;
        String data = "X".repeat(1024); // 2KB
        int threadPoolSize = 50;

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
                jmsTemplate.convertAndSend("test-queue", msg);

//                jmsTemplate.convertAndSend("test-queue", msg, message -> {
//                    message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
//                    return message;
//                });
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
}