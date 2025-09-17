package com.example.webfluxllm.service.facade;

import com.example.webfluxllm.model.facade.FacadeHomeResponseDto;
import reactor.core.publisher.Mono;

public interface FacadeService {

    Mono<FacadeHomeResponseDto> getFacadeHomeResponseDto();
}
