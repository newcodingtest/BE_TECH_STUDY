package com.example.webfluxllm.service.llmclient.gemini;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GeminiMessageRole {
    USER,
    MODEL
    ;

    /**
     * 직렬화 시 통일성을 위해서 일괄적으로 소문자 적용
     * */
    @JsonValue
    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
