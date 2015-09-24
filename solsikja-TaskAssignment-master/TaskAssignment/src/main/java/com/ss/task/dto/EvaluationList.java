package com.ss.task.dto;

import com.ss.task.model.EvaluationEntity;

import java.util.List;

/**
 * Created by LiYm on 2015/7/23.
 */
public class EvaluationList {

    List<EvaluationEntity> data;

    public EvaluationList() {
    }

    public List<EvaluationEntity> getData() {
        return data;
    }

    public void setData(List<EvaluationEntity> data) {
        this.data = data;
    }
}
