package com.ss.task.action;

import com.ss.task.action.auth.UserAuthPassport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liymm on 2015-03-24.
 */
@Controller
@RequestMapping("/dept/")
@UserAuthPassport
public class DeptAction {

    @RequestMapping("add")
    public String addUserPage() {
        return "dept/add";
    }

    @RequestMapping("list")
    public String listUserPage() {
        return "dept/list";
    }
}
