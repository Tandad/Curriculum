package com.ss.task.action;

import com.ss.task.action.auth.UserAuthPassport;
import com.ss.task.model.UserEntity;
import com.ss.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by liymm on 2015-03-24.
 */
@Controller
@RequestMapping("/user/")
@UserAuthPassport
public class UserAction {

    @Autowired
    UserService userService;

    @RequestMapping("add")
    public String addUserPage() {
        return "user/add";
    }

    @RequestMapping("list")
    public String listUserPage(ModelMap map) {

        map.addAttribute("depts", userService.getDepts().getData());

        return "user/list";
    }

    @RequestMapping("modifypw")
    public String modifypwPage() {
        return "user/modifypw";
    }
}
