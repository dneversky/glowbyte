package com.glowbyte.task.exception;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;

@JsonIncludeProperties(value = {"code", "message"})
@Getter
public class InvariantException extends RuntimeException {

    private final int code;
    private final String message;

    public InvariantException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
