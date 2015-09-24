package com.ss.task.dto;

import com.ss.task.model.UserEntity;

/**
 * Created by LiYm on 2015/7/24.
 */
public class UserEvaStatInfo implements Comparable<UserEvaStatInfo> {

    UserEntity user;

    double leader;
    long countOfLeader;

    double otherLeader;
    long countOfOtherLeader;

    double otherDept;
    long countOfOtherDept;

    double total;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public double getLeader() {
        return leader;
    }

    public void setLeader(double leader) {
        this.leader = leader;
    }

    public long getCountOfLeader() {
        return countOfLeader;
    }

    public void setCountOfLeader(long countOfLeader) {
        this.countOfLeader = countOfLeader;
    }

    public double getOtherLeader() {
        return otherLeader;
    }

    public void setOtherLeader(double otherLeader) {
        this.otherLeader = otherLeader;
    }

    public long getCountOfOtherLeader() {
        return countOfOtherLeader;
    }

    public void setCountOfOtherLeader(long countOfOtherLeader) {
        this.countOfOtherLeader = countOfOtherLeader;
    }

    public double getOtherDept() {
        return otherDept;
    }

    public void setOtherDept(double otherDept) {
        this.otherDept = otherDept;
    }

    public long getCountOfOtherDept() {
        return countOfOtherDept;
    }

    public void setCountOfOtherDept(long countOfOtherDept) {
        this.countOfOtherDept = countOfOtherDept;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "UserEvaStatInfo{" +
                "user=" + user +
                ", leader=" + leader +
                ", countOfLeader=" + countOfLeader +
                ", otherLeader=" + otherLeader +
                ", countOfOtherLeader=" + countOfOtherLeader +
                ", otherDept=" + otherDept +
                ", countOfOtherDept=" + countOfOtherDept +
                ", total=" + total +
                "}\n";
    }

    @Override
    public int compareTo(UserEvaStatInfo o) {
        if (total > o.total)
            return -1;
        else if (total < o.total)
            return 1;
        else
            return 0;
    }
}
