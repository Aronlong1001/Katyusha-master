package com.katyusha.aron.demo.event;

import com.katyusha.aron.library.model.BaseEvent;
import com.katyusha.aron.library.model.ErrorInfo;

/**
 * Created by aron on 2017/10/10.
 */

public class HomeEvent extends BaseEvent {

    private HomeResponse response;

    public HomeEvent(boolean isSuccess, ErrorInfo errorInfo) {
        super(isSuccess, errorInfo);
    }

    public HomeResponse getResponse() {
        return response;
    }

    public void setResponse(HomeResponse response) {
        this.response = response;
    }
}
