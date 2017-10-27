package com.katyusha.aron.demo.bean;

import java.io.Serializable;

/**
 * Created by aron on 2017/6/13.
 */

public class HomeItemBannerBean implements Serializable{

    private int itemType;
    private String img;
    private String value;
    private int type;
    private String title;
    private String c3Name;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
