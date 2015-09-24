package com.ss.webutil.wechat.token;

/**
 * Created by liymm on 2015-03-03.
 */
public class AccessToken {
    String access_token;
    int expires_in;

    public AccessToken() {
    }

    public AccessToken(int expires_in, String access_token) {
        this.expires_in = expires_in;
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}