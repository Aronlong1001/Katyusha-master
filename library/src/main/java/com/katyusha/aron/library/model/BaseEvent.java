package com.katyusha.aron.library.model;

/**
 * Created by aron on 2017/10/10.
 */

public class BaseEvent {

    protected boolean isSuccess;
    private ErrorInfo errorInfo;

    public BaseEvent() {
    }

    public BaseEvent(boolean isSuccess, ErrorInfo errorInfo) {
        this.isSuccess = isSuccess;
        this.errorInfo = errorInfo;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "BaseEvent{" +
                "isSuccess=" + isSuccess +
                '}';
    }
}
