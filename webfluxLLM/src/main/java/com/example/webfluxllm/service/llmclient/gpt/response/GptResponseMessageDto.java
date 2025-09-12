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
public class GptResponseMessageDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 855078179427403784L;

    private String content;

}
