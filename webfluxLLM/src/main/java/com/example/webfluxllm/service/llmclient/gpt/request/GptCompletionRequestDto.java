package com.example.webfluxllm.service.llmclient.gpt.request;

import com.example.webfluxllm.service.llmclient.gpt.GptMessageRole;
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
public class GptCompletionRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -1065534431995548184L;

    private GptMessageRole role;

    private String content;
}
