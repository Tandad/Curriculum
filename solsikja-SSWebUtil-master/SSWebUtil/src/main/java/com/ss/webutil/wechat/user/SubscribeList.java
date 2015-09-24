package com.ss.webutil.wechat.user;

import java.util.List;

/**
 * Created by LiYm on 2015/7/7.
 */
public class SubscribeList {
    Integer total;
    Integer count;
    String next_openid;
    SubscribeListData data;

    public SubscribeList() {
    }

    public SubscribeList(Integer total, Integer count, String next_openid, SubscribeListData data) {
        this.total = total;
        this.count = count;
        this.next_openid = next_openid;
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public SubscribeListData getData() {
        return data;
    }

    public void setData(SubscribeListData data) {
        this.data = data;
    }
}