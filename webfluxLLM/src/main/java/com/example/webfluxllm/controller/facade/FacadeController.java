package com.example.webfluxllm.controller.facade;

import com.example.webfluxllm.model.facade.FacadeHomeResponseDto;
import com.example.webfluxllm.service.facade.FacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/facade")
public class FacadeController {

    private final FacadeService facadeService;

    @PostMapping("/home")
    public Mono<FacadeHomeResponseDto> homeFacade(){
        return facadeService.getFacadeHomeResponseDto();
    }

}
