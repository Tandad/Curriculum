package com.ss.webutil.wechat.user;

import java.util.List;

/**
 * Created by LiYm on 2015/7/7.
 */
public class SubscribeListData {
    List<String> openid;

    public SubscribeListData() {
    }

    public SubscribeListData(List<String> openid) {
        this.openid = openid;
    }

    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }
}
