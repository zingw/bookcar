package com.java.backend.exception;

import com.java.backend.constant.ResponseStatus;

public class BookCarException extends RuntimeException {

    String detail;

    public BookCarException(ResponseStatus status) {
        super(status.name());
        this.detail = status.getDetail();
    }
}
