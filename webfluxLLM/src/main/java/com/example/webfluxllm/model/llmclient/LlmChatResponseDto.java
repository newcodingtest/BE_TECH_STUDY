package com.example.webfluxllm.model.llmclient;

import com.example.webfluxllm.service.llmclient.gemini.response.GeminiChatResponseDto;
import com.example.webfluxllm.service.llmclient.gpt.response.GptChatResponseDto;
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
public class LlmChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -4108112449093530823L;

    private String llmResponse;

    public LlmChatResponseDto(GptChatResponseDto gptChatResponseDto){
        this.llmResponse = gptChatResponseDto.getSingleChoice().getMessage().getContent();
    }

    public static LlmChatResponseDto getLlmChatResponseFromStream(GptChatResponseDto gptChatResponseDto){
        return new LlmChatResponseDto(gptChatResponseDto.getSingleChoice().getDelta().getContent());
    }

    public LlmChatResponseDto(GeminiChatResponseDto geminiChatResponseDto){
        this.llmResponse = geminiChatResponseDto.getSingleText();
    }
}
