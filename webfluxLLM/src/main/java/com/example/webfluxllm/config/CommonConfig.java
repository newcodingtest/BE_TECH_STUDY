package com.example.webfluxllm.config;


import com.example.webfluxllm.model.llmclient.LlmType;
import com.example.webfluxllm.service.llmclient.LlmWebClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class CommonConfig {

    @Bean
    public Map<LlmType, LlmWebClientService> getLlmWebClientServiceMap(List<LlmWebClientService> llmWebClientServices){
        return llmWebClientServices.stream().collect(Collectors.toMap(LlmWebClientService::getLlmType, Function.identity()));
    }
}
