package com.ss.webutil.wechat.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.net.HttpSSL;

import java.io.IOException;

/**
 * Created by LiYm on 2015/7/6.
 */
public class UserHelper {

    public static WechatUser getInfo(ObjectMapper objectMapper, String openid, String accessToken) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/cgi-bin/user/info?access_token=").append(accessToken)
                .append("&openid=").append(openid).append("&lang=zh_CN");

        String resp = HttpSSL.get(url.toString());

        System.out.println(resp);

        try {
            return objectMapper.readValue(resp, WechatUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static SubscribeList getSubscribeList(ObjectMapper objectMapper, String next, String accessToken) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/cgi-bin/user/get?access_token=").append(accessToken);

        if (next != null)
            url.append("&next_openid=").append(next);

        String resp = HttpSSL.get(url.toString());
        System.out.println(resp);

        try {
            return objectMapper.readValue(resp, SubscribeList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
