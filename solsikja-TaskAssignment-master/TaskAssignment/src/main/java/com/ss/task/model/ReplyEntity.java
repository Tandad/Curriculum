package com.ss.task.model;

import java.util.Date;

/**
 * Created by liymm on 2015-04-17.
 */
public class ReplyEntity {
    private Integer id;
    private String content;
    private Date pubtime;
    private TaskEntity task;
    private UserEntity user;
    private UserEntity replyuser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getReplyuser() {
        return replyuser;
    }

    public void setReplyuser(UserEntity replyuser) {
        this.replyuser = replyuser;
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }
}
