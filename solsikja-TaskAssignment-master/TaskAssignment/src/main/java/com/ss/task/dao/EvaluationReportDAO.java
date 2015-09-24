package com.ss.task.dao;

import com.ss.task.model.EvaluationReportEntity;
import com.ss.webutil.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.sql.Date;

/**
 * Created by LiYm on 2015/7/27.
 */
@Repository
public class EvaluationReportDAO extends BaseDAO<EvaluationReportEntity> {

    public Date getLastWeekReportDate() {
        String hql = "select max(e.startDate) from EvaluationReportEntity e where e.type = :type";
        return (Date)getSession().createQuery(hql).setInteger("type", EvaluationReportEntity.WEEK).uniqueResult();
    }

    public Date getLastMonthReportDate() {
        String hql = "select max(e.endDate) from EvaluationReportEntity e where e.type = :type";
        return (Date)getSession().createQuery(hql).setInteger("type", EvaluationReportEntity.MONTH).uniqueResult();
    }

}
