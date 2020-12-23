package com.turing.circuit_analysis_website.exception;

import com.turing.circuit_analysis_website.enums.CustomizeErrorCode;

/**
 * @author CHEN
 * @date 2020/10/13  18:28
 */
public class CustomizeRuntimeException extends RuntimeException {
    private Integer code;
    private String message;

    public CustomizeRuntimeException(CustomizeErrorCode customizeErrorCode) {

        this.code = customizeErrorCode.getCode();
        this.message = customizeErrorCode.getMessage();

    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
