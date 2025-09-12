package com.example.webfluxllm.service.llmclient.gpt.request;

import com.example.webfluxllm.model.llmclient.LlmChatRequestDto;
import com.example.webfluxllm.model.llmclient.LlmModel;
import com.example.webfluxllm.service.llmclient.gpt.GptMessageRole;
import com.example.webfluxllm.service.llmclient.gpt.response.GptChatResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GptChatRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -9201879791756973432L;

    private List<GptCompletionRequestDto> messages;
    private LlmModel llmModel;
    private Boolean stream;
    private GptResponseFormat response_format;

    public GptChatRequestDto(LlmChatRequestDto llmChatRequestDto){
        if (llmChatRequestDto.isUseJson()){
            response_format = new GptResponseFormat("json_object");
        }
        this.messages = List.of(new GptCompletionRequestDto(GptMessageRole.SYSTEM, llmChatRequestDto.getSystemPrompt()),
                new GptCompletionRequestDto(GptMessageRole.SYSTEM, llmChatRequestDto.getSystemPrompt()));
        this.llmModel = llmChatRequestDto.getLlmModel();
    }

}
