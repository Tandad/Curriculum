package com.ss.task.dto;

import com.ss.task.model.EvaTypeEntity;
import com.ss.task.model.UserEntity;

/**
 * Created by LiYm on 2015/7/24.
 */
public class EvaStatInfo {

    UserEntity user;
    EvaTypeEntity type;
    Long count;
    Double score;
    Double weightScore;

    public EvaStatInfo() {
    }

    public EvaStatInfo(UserEntity user, EvaTypeEntity type, Long count, Double score, Double weightScore) {
        this.user = user;
        this.type = type;
        this.count = count;
        this.score = score;
        this.weightScore = weightScore;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EvaTypeEntity getType() {
        return type;
    }

    public void setType(EvaTypeEntity type) {
        this.type = type;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getWeightScore() {
        return weightScore;
    }

    public void setWeightScore(Double weightScore) {
        this.weightScore = weightScore;
    }

    @Override
    public String toString() {
        return "EvaStatInfo{" +
                "user=" + user +
                ", type=" + type +
                ", count=" + count +
                ", score=" + score +
                ", weightScore=" + weightScore +
                "}\n";
    }
}
