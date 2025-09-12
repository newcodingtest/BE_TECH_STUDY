package com.example.webfluxllm.service.llmclient.gpt;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GptMessageRole {
    SYSTEM,
    USER,
    ASSISTANT,
    ;

    @JsonValue
    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
