package com.ss.webutil.wechat.pay.jsapi;

import com.ss.webutil.util.Algorithm;
import com.ss.webutil.wechat.Config;
import com.ss.webutil.wechat.pay.KVPair;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiYm on 2015/8/13.
 */
public class PayParam {

    public enum SignType {
        MD5("MD5"),
        SHA1("SHA1");

        protected String value;

        SignType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    String appId;
    Long timeStamp;
    String nonceStr;    // 支付签名随机串，不长于 32 位
    String _package;    // 统一支付接口返回的prepay_id参数值，提交格式如：prepay_id=***）
    String signType;    // 签名方式，默认为'SHA1'，使用新版支付需传入'MD5'
    String paySign;

    public PayParam(Config config, String pid) {
        appId = config.getAppID();
        timeStamp = System.currentTimeMillis() / 1000;
        nonceStr = Algorithm.MD5(timeStamp.toString());
        _package = String.format("prepay_id=%s", pid);
        signType = SignType.MD5.toString();

        generatePaySign(config);
    }

    void generatePaySign(Config config) {
        List<KVPair> pairList = new ArrayList<>(5);

        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {

            //不需要静态属性
            if ( (field.getModifiers() & Modifier.STATIC) != 0)
                continue;

            Object value;
            try {
                value = field.get(this);
                if (value == null)
                    continue;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                break;
            }

            KVPair pair = new KVPair();

            if ("_package".equals(field.getName()))
                pair.setKey("package");
            else
                pair.setKey(field.getName());

            pair.setValue(value.toString());
            pairList.add(pair);

            System.out.println(pair);
        }

        //System.out.println("paySign");
        paySign = KVPair.signature(pairList, config.getPayKey());
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackage() {
        return _package;
    }

    public void setPackage(String _package) {
        this._package = _package;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
