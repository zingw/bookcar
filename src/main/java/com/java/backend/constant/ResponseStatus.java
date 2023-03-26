package com.java.backend.constant;

import lombok.Getter;

@Getter
public enum ResponseStatus {
    USER_EXISTED("Người dùng này đã tồn tại"),
    USER_ID_IS_INVALID("Id này không tồn tại"),
    USERNAME_NOT_FOUND("Tên người dùng này không tồn tại"),
    USER_NAME_IS_INVALID("Kh"),
    CAN_NOT_PARSE_FILE("Không thể tải ảnh avatar"),
    PASSWORD_INCORRECT("Sai mật khẩu"),
    USER_NOT_ACTIVATED("Người dùng này chưa được kích hoạt"),
    INVALID_CREDENTIALS("Sai tên mật khẩu hoặc tài khoản");

    private final String detail;

    ResponseStatus(String detail) {
        this.detail = detail;
    }
}
