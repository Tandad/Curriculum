package com.ss.webutil.wechat.menu;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.net.HttpSSL;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liymm on 2015-04-02.
 */
public class Menu {
    MenuButton[] button;

    public MenuButton[] getButton() {
        return button;
    }

    public void setButton(MenuButton[] button) {
        this.button = button;
    }

}
