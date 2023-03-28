package com.java.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorMessage {

    private Map<String, String> invalidParams;
    private String message;
}
