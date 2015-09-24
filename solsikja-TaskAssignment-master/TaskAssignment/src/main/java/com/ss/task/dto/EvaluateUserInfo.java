package com.ss.task.dto;

import com.ss.task.model.UserEntity;

import java.util.List;

/**
 * Created by LiYm on 2015/7/22.
 */
public class EvaluateUserInfo {

    List<UserEntity> underling;
    List<UserEntity> otherUnderling;
    List<UserEntity> colleague;

    public EvaluateUserInfo() {
    }

    public List<UserEntity> getUnderling() {
        return underling;
    }

    public void setUnderling(List<UserEntity> underling) {
        this.underling = underling;
    }

    public List<UserEntity> getOtherUnderling() {
        return otherUnderling;
    }

    public void setOtherUnderling(List<UserEntity> otherUnderling) {
        this.otherUnderling = otherUnderling;
    }

    public List<UserEntity> getColleague() {
        return colleague;
    }

    public void setColleague(List<UserEntity> colleague) {
        this.colleague = colleague;
    }
}
