package com.ss.webutil.wechat;

/**
 * Created by liymm on 2015-04-01.
 */
public class Config {

    String appID;
    String appSecret;
    String token;
    String baseURL;
    String mch_id;
    String payKey;
    String serverIP;
    boolean develop;

    public Config() {
    }

    public Config(String appID, String appSecret, String token, String baseURL, String serverIP) {
        this.appID = appID;
        this.appSecret = appSecret;
        this.token = token;
        this.baseURL = baseURL;
        this.serverIP = serverIP;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public boolean isDevelop() {
        return develop;
    }

    public void setDevelop(boolean develop) {
        this.develop = develop;
    }
}
