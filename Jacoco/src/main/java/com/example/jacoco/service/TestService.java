package com.example.jacoco.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public int sum(int a, int b){
        return a+b;
    }

    public int minus(int a, int b){
        return a-b;
    }
}
