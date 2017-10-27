package com.katyusha.aron.demo.bean;

import java.io.Serializable;

/**
 * Created by aron on 2017/6/13.
 */

public class HomeItemDetailBean implements Serializable{

    private int type;
    private int itemType;
    private String img;
    private String value;
    private int position;
    private String title;
    private String c3Name;
    private String sysno;
    private String traceId;
    private HomeItemDetailItemBean product;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getC3Name() {
        return c3Name;
    }

    public void setC3Name(String c3Name) {
        this.c3Name = c3Name;
    }

    public String getSysno() {
        return sysno;
    }

    public void setSysno(String sysno) {
        this.sysno = sysno;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public HomeItemDetailItemBean getProduct() {
        return product;
    }

    public void setProduct(HomeItemDetailItemBean product) {
        this.product = product;
    }
}
