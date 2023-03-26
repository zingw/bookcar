package com.java.backend.dto.response;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

    private Map<String, String> invalidParams;
    private String message;
}
