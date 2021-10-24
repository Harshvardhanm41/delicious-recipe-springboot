package com.abnamro.spring.data.cassandra.exceptions;

public class ErrorResponse {
    private String errorCode;
    private String error_Msg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getError_Msg() {
        return error_Msg;
    }

    public void setError_Msg(String error_Msg) {
        this.error_Msg = error_Msg;
    }
}
