package com.ss.task.model;

import java.sql.Date;

/**
 * Created by LiYm on 2015/7/27.
 */
public class EvaluationReportEntity {

    public static final int WEEK = 0;
    public static final int MONTH = 1;

    private Integer id;
    private Date startDate;
    private Date endDate;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
