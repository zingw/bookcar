package com.java.backend.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class PageResponse<T> {

    private List<T> content;
    private long totalElement;

    @Builder
    public PageResponse(List<T> content, long totalElement) {
        this.content = content;
        this.totalElement = totalElement;
    }

    @Builder
    public static final class PageResponseBuilder<T> {

        private List<T> content;
        private long totalElement;

        public PageResponse<T> build() {
            return new PageResponse<>(content, totalElement);
        }
    }
}
