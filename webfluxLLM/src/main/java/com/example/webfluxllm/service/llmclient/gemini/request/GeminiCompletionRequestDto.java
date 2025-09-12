package com.example.webfluxllm.service.llmclient.gemini.request;


import com.example.webfluxllm.service.llmclient.gemini.GeminiMessageRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeminiCompletionRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -3696588448456039306L;

    private GeminiMessageRole role;

    private String content; //채팅 내용

    public GeminiCompletionRequestDto(String content) {
        this.content = content;
    }
}
