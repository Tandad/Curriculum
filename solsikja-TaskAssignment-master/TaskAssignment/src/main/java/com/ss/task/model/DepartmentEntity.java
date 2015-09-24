package com.ss.task.model;

/**
 * Created by liymm on 2015-03-23.
 */
public class DepartmentEntity {

    static public final int ISEVALUATE = 1;
    static public final int NOTEVALUATE = 0;

    private Integer id;
    private String title;
    private Integer evaluate;
    private Integer evauser;
    private String evausername;

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

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getEvauser() {
        return evauser;
    }

    public void setEvauser(Integer evauser) {
        this.evauser = evauser;
    }

    public String getEvausername() {
        return evausername;
    }

    public void setEvausername(String evausername) {
        this.evausername = evausername;
    }
}
