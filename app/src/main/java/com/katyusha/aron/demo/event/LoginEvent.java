package com.katyusha.aron.demo.event;

import com.katyusha.aron.library.model.BaseEvent;
import com.katyusha.aron.library.model.BaseResponse;
import com.katyusha.aron.library.model.ErrorInfo;

/**
 * Created by aron on 2017/10/12.
 */

public class LoginEvent extends BaseEvent {

    private BaseResponse response;

    public LoginEvent(boolean isSuccess, ErrorInfo errorInfo) {
        super(isSuccess, errorInfo);
    }

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }
}
