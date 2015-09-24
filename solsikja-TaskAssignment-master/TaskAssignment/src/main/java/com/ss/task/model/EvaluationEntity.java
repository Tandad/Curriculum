package com.ss.task.model;

import java.sql.Date;

/**
 * Created by LiYm on 2015/7/22.
 */
public class EvaluationEntity {

    public static final int UNCOMPLETE = 0;
    public static final int COMPLETE = 1;

    private Integer id;
    private UserEntity user;
    private UserEntity evauser;
    private EvaTypeEntity type;
    private Integer score;
    private Integer complete;
    private Date evadate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getEvadate() {
        return evadate;
    }

    public void setEvadate(Date evadate) {
        this.evadate = evadate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getEvauser() {
        return evauser;
    }

    public void setEvauser(UserEntity evauser) {
        this.evauser = evauser;
    }

    public EvaTypeEntity getType() {
        return type;
    }

    public void setType(EvaTypeEntity type) {
        this.type = type;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }
}
