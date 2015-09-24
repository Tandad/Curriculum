package com.ss.task.model;

import java.sql.Date;

/**
 * Created by liymm on 2015-03-23.
 */
public class TaskEntity {

    public static final int TASK_PUB = 0;
    public static final int TASK_FINISHED = 1;
    public static final int TASK_EVALUATE = 2;

    private Integer id;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;
    private Date pubDate;
    private String comment;
    private Integer status;
    private UserEntity leader;
    private TaskTypeEntity type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserEntity getLeader() {
        return leader;
    }

    public void setLeader(UserEntity leader) {
        this.leader = leader;
    }

    public TaskTypeEntity getType() {
        return type;
    }

    public void setType(TaskTypeEntity type) {
        this.type = type;
    }
}
