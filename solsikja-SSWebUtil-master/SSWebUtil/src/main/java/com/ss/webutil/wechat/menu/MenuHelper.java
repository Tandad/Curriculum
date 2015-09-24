package com.ss.webutil.wechat.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.net.HttpSSL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiYm on 2015/5/15.
 */
public class MenuHelper {


    static Menu generate() {
        Menu menu = new Menu();

        MenuButton[] buttons = new MenuButton[3];
        buttons[0] = new MenuButton("view", "美业网站", null, "http://wechat.meiye.tv", null);
        buttons[1] = new MenuButton("view", "网站介绍", null, "http://wechat.meiye.tv", null);
        buttons[2] = new MenuButton("view", "联系我们", null, "http://wechat.meiye.tv", null);

        List<MenuButton> subButtons = new ArrayList<MenuButton>();
        subButtons.add(new MenuButton("click", "美发师的主页", "employee", null, null));
        subButtons.add(new MenuButton("click", "把店搬到手机上", "shop", null, null));
        buttons[1].setSub_button(subButtons);

        menu.setButton(buttons);

        return menu;
    }

    public static void createMenu(String accessToken, ObjectMapper objectMapper, Menu menu) {
        StringBuilder url = new StringBuilder();
        url.append("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=").append(accessToken);
        try {
            String menuStr = objectMapper.writeValueAsString(menu);

            System.out.println("==========================");
            System.out.println(menuStr);
            System.out.println("==========================");

            String resp = HttpSSL.post(url.toString(), menuStr);
            System.out.println("Create Menu" + resp);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
