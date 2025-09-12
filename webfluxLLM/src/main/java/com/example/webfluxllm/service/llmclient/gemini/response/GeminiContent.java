package com.example.webfluxllm.service.llmclient.gemini.response;

import com.example.webfluxllm.service.llmclient.gemini.GeminiMessageRole;
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
public class GeminiContent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1250952780238669448L;

    private List<GeminiPart> parts;

    private GeminiMessageRole role;

    public GeminiContent(List<GeminiPart> parts) {
        this.parts = parts;
    }
}
