package com.katyusha.aron.demo.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by aron on 2017/6/13.
 */

public class HomeBean implements Serializable{

    private int lotType;
    private int modelID;
    private String name;
    private int sortnum;
    private int isHasSpace;
    private int isNotificationBar;
    private List<HomeItemBean> list;
    private List<HomeItemDetailBean> modelSEDetail;
    private HomeItemBannerBean banner;

    public int getLotType() {
        return lotType;
    }

    public void setLotType(int lotType) {
        this.lotType = lotType;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSortnum() {
        return sortnum;
    }

    public void setSortnum(int sortnum) {
        this.sortnum = sortnum;
    }

    public int getIsHasSpace() {
        return isHasSpace;
    }

    public void setIsHasSpace(int isHasSpace) {
        this.isHasSpace = isHasSpace;
    }

    public int getIsNotificationBar() {
        return isNotificationBar;
    }

    public void setIsNotificationBar(int isNotificationBar) {
        this.isNotificationBar = isNotificationBar;
    }

    public List<HomeItemBean> getList() {
        return list;
    }

    public void setList(List<HomeItemBean> list) {
        this.list = list;
    }

    public List<HomeItemDetailBean> getModelSEDetail() {
        return modelSEDetail;
    }

    public void setModelSEDetail(List<HomeItemDetailBean> modelSEDetail) {
        this.modelSEDetail = modelSEDetail;
    }

    public HomeItemBannerBean getBanner() {
        return banner;
    }

    public void setBanner(HomeItemBannerBean banner) {
        this.banner = banner;
    }
}
