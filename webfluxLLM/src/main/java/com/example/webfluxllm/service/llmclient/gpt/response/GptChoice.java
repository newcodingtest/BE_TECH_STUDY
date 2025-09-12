package com.example.webfluxllm.service.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GptChoice implements Serializable {
    @Serial
    private static final long serialVersionUID = -5804753165612074967L;

    private String finishReason;
    private GptResponseMessageDto message;
}
