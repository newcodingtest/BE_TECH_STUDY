package com.example.webfluxllm.model.llmclient;

import com.example.webfluxllm.model.user.chat.UserChatRequestDto;
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
public class LlmChatRequestDto implements Serializable {


    @Serial
    private static final long serialVersionUID = 1468520052611699262L;

    private String userRequest;

    /**
     * systemPrompt 가 userRequest에 포함되는 내용보다 더 높은 강제성과 우선순위를 갖는다.
     */
    private String systemPrompt;
    private boolean useJson;
    private LlmModel llmModel;

    public LlmChatRequestDto(UserChatRequestDto llmChatRequestDto, String systemPrompt) {
        this.llmModel = llmChatRequestDto.getLlmModel();
        this.systemPrompt = systemPrompt;
        this.userRequest = llmChatRequestDto.getRequest();
    }
}
