package com.ss.task.dao;

import com.ss.task.dto.EvaStatInfo;
import com.ss.task.model.EvaTypeEntity;
import com.ss.task.model.EvaluationEntity;
import com.ss.task.model.UserEntity;
import com.ss.webutil.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by LiYm on 2015/7/23.
 */
@Repository
public class EvaluationDAO extends BaseDAO<EvaluationEntity> {

    public Date getLastEvaluationDate(Integer euid) {
        String hql = "select max(e.evadate) from EvaluationEntity e where e.evauser.id = :euid";
        return (Date)getSession().createQuery(hql)
                .setInteger("euid", euid).uniqueResult();
    }

    public List<EvaluationEntity> getEvaListByEvaUserByDate(Integer euid, Date evadate) {
        String hql = "from EvaluationEntity e where e.evauser.id = :euid and e.evadate = :evadate order by e.type.id";
        return getSession().createQuery(hql)
                .setInteger("euid", euid).setDate("evadate", evadate)
                .list();
    }

    public List<EvaluationEntity> getLeaderEvaListByWeek(Date start, Date end) {
        String hql = "from EvaluationEntity e where e.evadate >= :start and e.evadate <= :end and e.type.id = :type order by e.score desc";
        return getSession().createQuery(hql)
                .setDate("start", start).setDate("end", end)
                .setInteger("type", EvaTypeEntity.LEADER)
                .list();
    }

    public List<EvaStatInfo> getEvaStatistics(Date start, Date end) {
        String hql = "select new com.ss.task.dto.EvaStatInfo(e.user, e.type, count(e.score), avg(e.score), avg(e.score) * e.type.coefficient) " +
                "from EvaluationEntity e where e.evadate >= :start and e.evadate <= :end group by e.user, e.type";
        return getSession().createQuery(hql)
                .setDate("start", start).setDate("end", end)
                .list();
    }

    public List<UserEntity> getLeaderUncompleteUserListByDate(Date start, Date end) {
        String hql = "select distinct e.user from EvaluationEntity e where e.evadate >= :start and e.evadate <= :end and e.type.id = :type and e.complete = :complete";
        return getSession().createQuery(hql)
                .setDate("start", start).setDate("end", end)
                .setInteger("type", EvaTypeEntity.LEADER).setInteger("complete", EvaluationEntity.UNCOMPLETE)
                .list();
    }

    public List<EvaluationEntity> getLeaderEvaListByUserByDate(Integer uid, Date start, Date end) {
        String hql = "from EvaluationEntity e where e.evadate >= :start and e.evadate <= :end and e.type.id = :type and e.user.id = :uid order by e.evadate desc";
        return getSession().createQuery(hql)
                .setDate("start", start).setDate("end", end)
                .setInteger("type", EvaTypeEntity.LEADER).setInteger("uid", uid)
                .list();
    }

}
