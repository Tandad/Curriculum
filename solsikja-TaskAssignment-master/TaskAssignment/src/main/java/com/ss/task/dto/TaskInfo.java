package com.ss.task.dto;

import com.ss.task.model.TaskEntity;
import com.ss.task.model.TaskUserEntity;

import java.util.List;

/**
 * Created by liymm on 2015-03-31.
 */
public class TaskInfo {

    TaskEntity task;
    List<TaskUserEntity> members;

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public List<TaskUserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<TaskUserEntity> members) {
        this.members = members;
    }
}
