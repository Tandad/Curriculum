package com.ss.webutil.wechat.token;

/**
 * Created by liymm on 2015-03-03.
 */
public class Signature {

    String noncestr;
    long timestamp;
    String signature;

    public Signature() {
    }

    public Signature(String noncestr, long timestamp, String signature) {
        this.noncestr = noncestr;
        this.timestamp = timestamp;
        this.signature = signature;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
