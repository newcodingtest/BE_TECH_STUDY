package com.example.jacoco.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestServiceTest {

    @Autowired
    private TestService testService;

    @Test
    void 두개의_파라미터를_입력받으면_합산된_값을_리턴한다(){
        //given
        int p1 = 10;
        int p2 = 20;

        //when
        int result = testService.sum(p1,p2);

        //then
        Assertions.assertEquals(p1+p2, result);
    }
}
