package com.example.webfluxllm.service.llmclient.gemini.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class GeminiGenerationConfigDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 5229514112658976322L;

    private String responseMimeType;

    public GeminiGenerationConfigDto(){
        this.responseMimeType = "application/json";
    }


}
