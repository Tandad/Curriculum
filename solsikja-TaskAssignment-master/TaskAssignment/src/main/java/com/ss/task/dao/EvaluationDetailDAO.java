package com.ss.task.dao;

import com.ss.task.model.EvaluationDetailEntity;
import com.ss.webutil.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by LiYm on 2015/7/27.
 */
@Repository
public class EvaluationDetailDAO extends BaseDAO<EvaluationDetailEntity> {

    public List<EvaluationDetailEntity> listByReport(Integer rid) {
        String hql = "from EvaluationDetailEntity e where e.report.id = :rid order by e.total desc";
        return getSession().createQuery(hql).setInteger("rid", rid).list();
    }


}
