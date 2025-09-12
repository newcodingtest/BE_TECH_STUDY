package com.example.webfluxllm.service.llmclient.gemini.response;

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
public class GeminiCandidate implements Serializable {
    @Serial
    private static final long serialVersionUID = 2872354748074570629L;

    private GeminiContent content;
}
