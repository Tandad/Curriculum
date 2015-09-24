package com.ss.task.util;

import com.ss.task.model.UserEntity;

import javax.servlet.http.HttpSession;

/**
 * Created by liymm on 2015-03-31.
 */
public class BaseController {

    public UserEntity getUser(HttpSession session) {
        return (UserEntity)session.getAttribute("user");
    }

}
