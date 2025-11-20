package com.example.webfluxllm.service.llmclient.gemini.response;

import com.example.webfluxllm.exception.CustomErrorType;
import com.example.webfluxllm.exception.ErrorTypeException;
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
public class GeminiChatResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -8931886227696822515L;

    private List<GeminiCandidate> candidates;

    public String getSingleText() {
        return candidates.stream().findFirst()
                .flatMap(candidate -> candidate.getContent().getParts().stream().findFirst()
                        .map(part -> part.getText()))
                .orElseThrow(() ->
                        new ErrorTypeException("[GPT Response] There is no choices.", CustomErrorType.GPT_RESPONSE_ERROR));
    }
}
