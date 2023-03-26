package com.java.backend.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class PageResponse<T> {

    private List<T> content;
    private long totalElement;
}
