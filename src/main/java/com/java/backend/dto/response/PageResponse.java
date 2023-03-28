package com.java.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public final class PageResponse<T> {

    private List<T> content;
    private long totalElement;
}
