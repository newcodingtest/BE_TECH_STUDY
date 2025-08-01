package com.example.rabbitmqperformance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BenchmarkMessage {
    private String payload;
    private long sentTime; // 송신 시각 (epoch ms)
}