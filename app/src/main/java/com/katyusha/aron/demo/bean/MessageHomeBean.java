package com.katyusha.aron.demo.bean;

import java.util.ArrayList;

/**
 * Created by aron on 2017/8/16.
 */

public class MessageHomeBean {

    private ArrayList<HomeMsgEntity> HomeMsg;
    private int TotalCount;

    public ArrayList<HomeMsgEntity> getHomeMsg() {
        return HomeMsg;
    }

    public void setHomeMsg(ArrayList<HomeMsgEntity> homeMsg) {
        HomeMsg = homeMsg;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public static class HomeMsgEntity {
        private int Type;//类型；11：ERPSystemMsg；12：协同工作:13：商品咨询；14：公司公告
        private String Content;//内容
        private String DateTime;//时间戳
        private int Count;//类型数量
        private int IsRead;//是否已读
        private int TotalCount;//总数量

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getDateTime() {
            return DateTime;
        }

        public void setDateTime(String dateTime) {
            DateTime = dateTime;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int count) {
            Count = count;
        }

        public int getIsRead() {
            return IsRead;
        }

        public void setIsRead(int isRead) {
            IsRead = isRead;
        }

        public int getTotalCount() {
            return TotalCount;
        }

        public void setTotalCount(int totalCount) {
            TotalCount = totalCount;
        }
    }

}
