package com.ss.task.dto;

import com.ss.task.model.TaskUserEntity;

import java.util.List;

/**
 * Created by liymm on 2015-03-31.
 */
public class TaskUsers {
    List<TaskUserEntity> taskusers;

    public List<TaskUserEntity> getTaskusers() {
        return taskusers;
    }

    public void setTaskusers(List<TaskUserEntity> taskusers) {
        this.taskusers = taskusers;
    }
}
