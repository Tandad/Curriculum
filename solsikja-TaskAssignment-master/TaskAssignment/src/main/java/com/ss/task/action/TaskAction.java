package com.ss.task.action;

import com.ss.task.action.auth.UserAuthPassport;
import com.ss.task.model.TaskEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liymm on 2015-03-30.
 */
@Controller
@RequestMapping("/task/")
@UserAuthPassport
public class TaskAction {

    @RequestMapping("add")
    public String addTask(TaskEntity task) {
        return "task/add";
    }

    @RequestMapping("list")
    public String listTask(TaskEntity task) {
        return "task/list";
    }

    @RequestMapping("my")
    public String listMyTask(TaskEntity task) {
        return "task/my";
    }

    @RequestMapping("member")
     public String listMyPubTask(TaskEntity task) {
        return "task/member";
    }
}
