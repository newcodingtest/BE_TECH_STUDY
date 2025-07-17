package com.example.activemqperformance.service;

import com.example.activemqperformance.dto.BenchmarkMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

@Component
public class PerformanceConsumer {

    private final AtomicInteger count = new AtomicInteger();
    private final LongAdder totalLatency = new LongAdder();
    private final long start = System.currentTimeMillis();

    @Transactional
    @JmsListener(destination = "test-queue", concurrency = "1")
    public void receive(BenchmarkMessage msg) {
        long latency = System.currentTimeMillis() - msg.getSentTime();
        totalLatency.add(latency);

        int received = count.incrementAndGet();
        if (received % 10000 == 0) {
            long elapsed = System.currentTimeMillis() - start;
            double tps = received / (elapsed / 1000.0);
            double avgLatency = totalLatency.doubleValue() / received;

            System.out.printf("Received: %,d, TPS: %.2f, Avg Latency: %.2fms%n",
                    received, tps, avgLatency);
        }
    }
}