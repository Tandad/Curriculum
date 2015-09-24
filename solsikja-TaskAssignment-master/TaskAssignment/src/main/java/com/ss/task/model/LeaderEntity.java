package com.ss.task.model;

/**
 * Created by liymm on 2015-03-23.
 */
public class LeaderEntity {
    private Integer id;
    private String title;
    private DepartmentEntity department;
    private UserEntity leader;

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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public UserEntity getLeader() {
        return leader;
    }

    public void setLeader(UserEntity leader) {
        this.leader = leader;
    }
}
