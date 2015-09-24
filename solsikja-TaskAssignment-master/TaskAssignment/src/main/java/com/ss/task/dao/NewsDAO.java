package com.ss.task.dao;

import com.ss.task.dto.NewsInfo;
import com.ss.task.model.NewsEntity;
import com.ss.webutil.dao.BaseDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liymm on 2015-04-17.
 */
@Repository
public class NewsDAO extends BaseDAO<NewsEntity> {

    public List<NewsInfo> getNewsList(Integer offset, Integer length) {
        String hql = "select new com.ss.task.dto.NewsInfo(n.id, n.title, n.pubtime) from NewsEntity n order by n.id desc";
        return getSession().createQuery(hql).setFirstResult(offset).setMaxResults(length).list();
    }
}
