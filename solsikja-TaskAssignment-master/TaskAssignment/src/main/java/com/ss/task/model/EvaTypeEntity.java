package com.ss.task.model;

/**
 * Created by LiYm on 2015/7/22.
 */
public class EvaTypeEntity {

    public static final int LEADER = 1;
    public static final int OTHER_LEADER = 2;
    public static final int OTHER_DEPT = 3;

    private Integer id;
    private String title;
    private Float coefficient;

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

    public Float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Float coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvaTypeEntity that = (EvaTypeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (coefficient != null ? !coefficient.equals(that.coefficient) : that.coefficient != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (coefficient != null ? coefficient.hashCode() : 0);
        return result;
    }
}
