package com.example.webfluxllm.service.llmclient.gemini.request;

import com.example.webfluxllm.model.llmclient.LlmChatRequestDto;
import com.example.webfluxllm.service.llmclient.gemini.GeminiMessageRole;
import com.example.webfluxllm.service.llmclient.gemini.response.GeminiContent;
import com.example.webfluxllm.service.llmclient.gemini.response.GeminiPart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeminiChatRequestDto implements Serializable {


    @Serial
    private static final long serialVersionUID = -3477668395126391728L;

    private List<GeminiContent> contents;
    private GeminiContent systemInstruction;
    private GeminiGenerationConfigDto generationConfig;

    public GeminiChatRequestDto(LlmChatRequestDto llmChatRequestDto) {
        if (llmChatRequestDto.isUseJson()){
            this.generationConfig = new GeminiGenerationConfigDto();
        }
        this.contents = List.of(new GeminiContent(List.of(new GeminiPart(llmChatRequestDto.getUserRequest())), GeminiMessageRole.USER));
        this.systemInstruction = new GeminiContent(List.of(new GeminiPart(llmChatRequestDto.getUserRequest())));
    }
}
