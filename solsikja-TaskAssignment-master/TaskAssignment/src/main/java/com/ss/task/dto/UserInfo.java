package com.ss.task.dto;

import com.ss.task.model.DepartmentEntity;
import com.ss.task.model.UserEntity;

import java.util.List;

/**
 * Created by liymm on 2015-03-24.
 */
public class UserInfo {

    UserEntity user;
    List<DepartmentEntity> depts;

    public UserInfo() {
    }

    public UserInfo(UserEntity user, List<DepartmentEntity> depts) {
        this.user = user;
        this.depts = depts;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<DepartmentEntity> getDepts() {
        return depts;
    }

    public void setDepts(List<DepartmentEntity> depts) {
        this.depts = depts;
    }
}
