package com.ss.webutil.wechat.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by liymm on 2015-04-01.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class LoginAccessToken {

    String openid;
    String access_token;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
