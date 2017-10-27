package com.katyusha.aron.demo.bean;

import java.io.Serializable;

/**
 * Created by aron on 2017/6/13.
 */

public class HomeItemPriceBean implements Serializable{
    private String hasOrigPrice;
    private String price;
    private String priceName;
    private String origPrice;
    private String origPriceName;

    public String getHasOrigPrice() {
        return hasOrigPrice;
    }

    public void setHasOrigPrice(String hasOrigPrice) {
        this.hasOrigPrice = hasOrigPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceName() {
        return priceName;
    }

    public void setPriceName(String priceName) {
        this.priceName = priceName;
    }

    public String getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(String origPrice) {
        this.origPrice = origPrice;
    }

    public String getOrigPriceName() {
        return origPriceName;
    }

    public void setOrigPriceName(String origPriceName) {
        this.origPriceName = origPriceName;
    }
}
