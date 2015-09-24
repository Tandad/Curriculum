package com.ss.task.rest;

import com.ss.task.dto.UserInfo;
import com.ss.task.model.DepartmentEntity;
import com.ss.task.model.LeaderEntity;
import com.ss.task.model.UserEntity;
import com.ss.task.service.UserService;
import com.ss.task.util.ErrorInfo;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liymm on 2015-03-23.
 */
@RestController
@RequestMapping("/rest/user/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("add")
    public ResultModel<UserEntity> addUser(String name, String realname, Integer admin) {

        if (name == null)
            return new ResultModel<UserEntity>(ErrorInfo.INVALID_PARAMETER);

        if (realname == null)
            return new ResultModel<UserEntity>(ErrorInfo.INVALID_PARAMETER);

        return userService.addUser(name, realname, null, null, admin);
    }

    @RequestMapping("list")
    public ResultModel<List<UserEntity>> listUser(Integer offset, Integer length) {

        if (offset == null)
            return new ResultModel<List<UserEntity>>(ErrorInfo.INVALID_PARAMETER);

        if (length == null)
            return new ResultModel<List<UserEntity>>(ErrorInfo.INVALID_PARAMETER);

        return userService.getUsers(offset, length);
    }

    @RequestMapping("listcount")
    public ResultModel<Long> countOfListUser() {
        return userService.getCountOfUsers();
    }

    @RequestMapping("remove")
    public BaseResult removeUser(Integer id) {

        if (id == null)
            return new BaseResult(ErrorInfo.INVALID_PARAMETER);

        return userService.removeUser(id);
    }

    @RequestMapping("modify")
    public ResultModel<UserEntity> modifyUser(Integer id, String realname, Integer did, String title) {

        if (did < 0)
            did = null;

        return userService.modifyUser(id, realname, did, title);
    }

    @RequestMapping("{id}")
    public ResultModel<UserInfo> getInfo(@PathVariable Integer id) {
        ResultModel<UserEntity> u = userService.getUser(id);

        if (u.getStatus() != ResultModel.SUCCESS)
            return new ResultModel<UserInfo>(ErrorInfo.INVALID_PARAMETER);

        ResultModel<List<DepartmentEntity>> d = userService.getDepts();

        return new ResultModel(new UserInfo(u.getData(), d.getData()));
    }

    @RequestMapping("{id}/dept/lead")
    public ResultModel<List<LeaderEntity>> getLeadDept(@PathVariable Integer id) {
        return userService.getLeaderByUser(id);
    }

    @RequestMapping("{id}/follower")
    public ResultModel<List<UserEntity>> getFollower(@PathVariable Integer id) {
        return userService.getUsersByLeader(id);
    }

    @RequestMapping("password/modify")
    public BaseResult modifyPassword(Integer id, String oldpw, String newpw, String reppw) {
        return userService.changePwd(id, oldpw, newpw, reppw);
    }

}
