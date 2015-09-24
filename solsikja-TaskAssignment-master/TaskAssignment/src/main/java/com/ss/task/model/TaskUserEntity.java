package com.ss.task.model;

import java.sql.Date;

/**
 * Created by liymm on 2015-03-23.
 */
public class TaskUserEntity {

    public static final int NOT_SUBMIT = 0;
    public static final int SUBMIT = 1;
    public static final int EVALUATE = 2;

    public static final int COMPLETE = 1;
    public static final int UMCOMPLETE = 0;

    private Integer id;
    private String detail;
    private String comment;
    private String report;
    private Integer status;
    private Date subdate;
    private Date evadate;
    private TaskEntity task;
    private UserEntity user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubdate() {
        return subdate;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }

    public Date getEvadate() {
        return evadate;
    }

    public void setEvadate(Date evadate) {
        this.evadate = evadate;
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
}
