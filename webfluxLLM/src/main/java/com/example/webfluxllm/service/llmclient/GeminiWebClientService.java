package com.example.webfluxllm.service.llmclient;


import com.example.webfluxllm.model.llmclient.LlmChatRequestDto;
import com.example.webfluxllm.model.llmclient.LlmChatResponseDto;
import com.example.webfluxllm.model.llmclient.LlmType;
import com.example.webfluxllm.service.llmclient.gemini.request.GeminiChatRequestDto;
import com.example.webfluxllm.service.llmclient.gemini.response.GeminiChatResponseDto;
import com.example.webfluxllm.service.llmclient.gpt.response.GptChatResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Service
public class GeminiWebClientService implements LlmWebClientService {

    private final WebClient webClient;

    @Value("${llm.gemini.key}")
    private String geminiApiKey;
    @Override
    public Mono<LlmChatResponseDto> getChatCompletion(LlmChatRequestDto requestDto) {

        GeminiChatRequestDto geminiChatRequestDto = new GeminiChatRequestDto(requestDto);
        return webClient.post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + geminiApiKey)
                .bodyValue(geminiChatRequestDto)
                .retrieve()//여기까지 이벤트 루프 쓰레드가 담당하고, 이후부터의 작업은 netty가 배정한 쓰레드가 담당
                .onStatus(HttpStatusCode::is4xxClientError, (clientResponse -> {
                    return clientResponse.bodyToMono(String.class).flatMap(body -> {
                        log.error("Error Response: {}", body);
                        return Mono.error(new RuntimeException("Api 요청 실패: " + body));
                    });
                }))
                .bodyToMono(GeminiChatResponseDto.class)
                .map(LlmChatResponseDto::new);
    }

    @Override
    public LlmType getLlmType() {
        return LlmType.GEMINI;
    }
}
