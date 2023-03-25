package com.java.backend.exception;

public class ExceptionUtils extends RuntimeException {

    public static final ExceptionUtils UNKNOWN_ERROR = new ExceptionUtils(Status.UNKNOWN_ERROR);
    public static final ExceptionUtils UNKNOWN_USERNAME = new ExceptionUtils(Status.UNKNOWN_USERNAME);
    public static final ExceptionUtils USERNAME_EMAIL_EXIST = new ExceptionUtils(Status.USERNAME_EMAIL_EXIST);

    public ExceptionUtils(Status status) {}

    public ExceptionUtils withMessage(String message) {
        this.setMessage(message);
        return this;
    }

    public ExceptionUtils build() {
        return this;
    }

    private void setMessage(String message) {}
}
