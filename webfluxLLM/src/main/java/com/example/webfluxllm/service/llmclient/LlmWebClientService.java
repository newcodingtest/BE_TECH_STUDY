package com.example.webfluxllm.service.llmclient;

import com.example.webfluxllm.model.llmclient.LlmChatRequestDto;
import com.example.webfluxllm.model.llmclient.LlmChatResponseDto;
import com.example.webfluxllm.model.llmclient.LlmType;
import reactor.core.publisher.Mono;

public interface LlmWebClientService {

    Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto);

    LlmType getLlmType();
}
