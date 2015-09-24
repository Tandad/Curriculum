package com.ss.task.model;

/**
 * Created by LiYm on 2015/7/27.
 */
public class EvaluationDetailEntity {
    private Integer id;
    private Double leader;
    private Double otherLeader;
    private Double otherDept;
    private Double total;
    private UserEntity user;
    private EvaluationReportEntity report;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLeader() {
        return leader;
    }

    public void setLeader(Double leader) {
        this.leader = leader;
    }

    public Double getOtherLeader() {
        return otherLeader;
    }

    public void setOtherLeader(Double otherLeader) {
        this.otherLeader = otherLeader;
    }

    public Double getOtherDept() {
        return otherDept;
    }

    public void setOtherDept(Double otherDept) {
        this.otherDept = otherDept;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public EvaluationReportEntity getReport() {
        return report;
    }

    public void setReport(EvaluationReportEntity report) {
        this.report = report;
    }
}
