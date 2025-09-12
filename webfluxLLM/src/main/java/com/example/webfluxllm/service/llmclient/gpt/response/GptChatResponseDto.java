package com.example.webfluxllm.service.llmclient.gpt.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GptChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4713353600783956590L;

    private List<GptChoice> choices;

    public GptChoice getSingleChoice(){
        return choices.stream().findFirst().orElseThrow();
    }

}
