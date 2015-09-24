package com.ss.webutil.wechat.pay;

import com.ss.webutil.util.Algorithm;
import com.ss.webutil.wechat.Config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by LiYm on 2015/6/8.
 */
public class BaseRequest {

    public static final String JSAPI = "JSAPI";

    String appid;
    String mch_id;
    String device_info;
    String nonce_str;
    String sign;

    public BaseRequest() {
    }

    public BaseRequest(Config config) {
        appid = config.getAppID();
        mch_id = config.getMch_id();
        nonce_str = Algorithm.MD5(Long.toString(System.currentTimeMillis()));
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void signature(Config config) {
        sign = computeSignature(config);

        System.out.println("sign=" + sign);
    }

    public String computeSignature(Config config) {
        List<KVPair> pairList = new ArrayList<>();

        for (Class<?> cls = getClass(); cls != Object.class; cls = cls.getSuperclass()) {

            Field[] fields = cls.getDeclaredFields();

            for (Field field : fields) {

                //不需要静态属性
                if ( (field.getModifiers() & Modifier.STATIC) != 0)
                    continue;

                //不统计sign字段
                if ("sign".equals(field.getName()))
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
                pair.setKey(field.getName());
                pair.setValue(value.toString());
                pairList.add(pair);

                System.out.println(pair);
            }
        }

        //System.out.println("sign");

        return KVPair.signature(pairList, config.getPayKey());
    }

    @Override
    public String toString() {
        return "BaseRequest{" +
                "appid='" + appid + '\'' +
                ", mch_id='" + mch_id + '\'' +
                ", device_info='" + device_info + '\'' +
                ", nonce_str='" + nonce_str + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
