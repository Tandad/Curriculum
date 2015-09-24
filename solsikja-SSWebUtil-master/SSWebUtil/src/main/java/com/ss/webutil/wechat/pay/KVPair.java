package com.ss.webutil.wechat.pay;

import com.ss.webutil.util.Algorithm;

import java.util.Collections;
import java.util.List;

/**
 * Created by LiYm on 2015/6/8.
 */
public class KVPair implements Comparable<KVPair> {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(key).append('=').append(value).toString();
    }

    public int compareTo(KVPair kvp) {
        return key.compareTo(kvp.key);
    }

    public static String signature(List<KVPair> pairs, String key) {
        Collections.sort(pairs);

        StringBuilder sb = new StringBuilder();
        for (KVPair kvp : pairs) {
            sb.append(kvp).append('&');
        }

        sb.append("key").append('=').append(key);

        //System.out.println(sb.toString());

        return Algorithm.MD5(sb.toString()).toUpperCase();
    }

}
