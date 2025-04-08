package com.athisii.authservice.dto;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/4/25
 */

public record ResponseDto<T>(String message, boolean status, T data) {
    public ResponseDto(String message, boolean status) {
        this(message, status, null);
    }

    public ResponseDto(String message, T data) {
        this(message, true, data);
    }

    public static <T> ResponseDto<T> onSuccess(String message, T data) {
        return new ResponseDto<>(message, data);
    }

    public static ResponseDto<Void> onFail(String message) {
        return new ResponseDto<>(message, false, null);
    }
}
