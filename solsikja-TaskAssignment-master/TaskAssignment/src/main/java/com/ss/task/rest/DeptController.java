package com.ss.task.rest;

import com.ss.task.model.DepartmentEntity;
import com.ss.task.model.LeaderEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.service.UserService;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liymm on 2015-03-24.
 */
@RestController
@RequestMapping("/rest/dept/")
public class DeptController {

    @Autowired
    UserService userService;

    @RequestMapping("add")
    public ResultModel<DepartmentEntity> addDept(String title) {
        return userService.addDept(title);
    }

    @RequestMapping("modify")
    public ResultModel<DepartmentEntity> modifyDept(Integer id, String title) {
        return userService.modifyDept(id, title);
    }

    @RequestMapping("evaluate/add")
    public BaseResult addEvaluate(Integer id) {
        return userService.setEvaluateForDept(id, DepartmentEntity.ISEVALUATE);
    }

    @RequestMapping("evaluate/del")
    public BaseResult delEvaluate(Integer id) {
        return userService.setEvaluateForDept(id, DepartmentEntity.NOTEVALUATE);
    }

    @RequestMapping("evaluate/user")
    public BaseResult setEvaluateUser(Integer id, Integer uid) {
        return userService.setEvaluateUser(id, uid);
    }

    @RequestMapping("remove")
    public BaseResult removeDept(Integer id) {
        return userService.removeDept(id);
    }

    @RequestMapping("list")
    public ResultModel<List<DepartmentEntity>> listDept() {
        return userService.getDepts();
    }

    @RequestMapping("{id}")
    public ResultModel<DepartmentEntity> getInfo(@PathVariable Integer id) {
        return userService.getDept(id);
    }

    @RequestMapping("{id}/members")
    public ResultModel<List<UserEntity>> getMembers(@PathVariable Integer id) {
        return userService.getUsersByDept(id);
    }

    @RequestMapping("{id}/leader/add")
    public ResultModel<LeaderEntity> addLeader(@PathVariable Integer id, Integer uid, String title) {
        return userService.addLeaderForDept(id, uid, title);
    }

    @RequestMapping("{id}/leader/remove")
    public BaseResult removeLeader(@PathVariable Integer id, Integer uid) {
        return userService.removeLeaderForDept(id, uid);
    }

    @RequestMapping("{id}/leaders")
    public ResultModel<List<LeaderEntity>> listLeaders(@PathVariable Integer id) {
        return userService.getLeaderByDept(id);
    }
}
