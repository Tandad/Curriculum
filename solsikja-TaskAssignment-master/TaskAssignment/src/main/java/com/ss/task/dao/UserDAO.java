package com.ss.task.dao;

import com.ss.task.model.DepartmentEntity;
import com.ss.task.model.UserEntity;
import com.ss.webutil.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liymm on 2015-03-23.
 */
@Repository
public class UserDAO extends BaseDAO<UserEntity> {

    public List<UserEntity> getUsersByLeader(Integer leader) {
        String hql = "select u from UserEntity u, LeaderEntity l"
                + " where u.department.id = l.department.id and l.leader.id = :leader"
                + " order by u.department.id";
        return getSession().createQuery(hql).setInteger("leader", leader).list();
    }

    public List<UserEntity> getEvaUsersByLeader(Integer leader) {
        String hql = "select u from UserEntity u, LeaderEntity l"
                + " where u.department.id = l.department.id and l.leader.id = :leader and u.department.evaluate = :evaluate"
                + " order by u.department.id";
        return getSession().createQuery(hql)
                .setInteger("leader", leader)
                .setInteger("evaluate", DepartmentEntity.ISEVALUATE)
                .list();
    }

    public List<UserEntity> getEvaUsersWithoutLeader(Integer leader) {
        String hql = "select u from UserEntity u, LeaderEntity l"
                + " where u.department.id != l.department.id and l.leader.id = :leader and u.department.evaluate = :evaluate"
                + " order by u.department.id";
        return getSession().createQuery(hql)
                .setInteger("leader", leader).setInteger("evaluate", DepartmentEntity.ISEVALUATE)
                .list();
    }

   public List<UserEntity> getEvaOtherDepartmentColleague(Integer uid) {
        String hql = "select u from UserEntity u where u.department.evaluate = :evaluate and u.department.evauser != :uid";
        return getSession().createQuery(hql)
                .setInteger("evaluate", DepartmentEntity.ISEVALUATE)
                .setInteger("uid", uid).list();
    }

}
