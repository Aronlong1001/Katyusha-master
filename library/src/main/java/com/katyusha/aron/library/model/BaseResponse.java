package com.katyusha.aron.library.model;

/**
 * Created by aron on 2017/10/10.
 */

public class BaseResponse<T> {

    private String error;
    private String message;
    private T data;

    public ErrorInfo getErrorInfo() {
        return new ErrorInfo(error, message);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
