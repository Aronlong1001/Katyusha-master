package com.katyusha.aron.demo.event;

/**
 * Created by aron on 2017/6/15.
 */

public class BhClickEvent {

    private String params;

    public BhClickEvent(String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
