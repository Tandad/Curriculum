package com.ss.task.service;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.ss.task.dao.NewsDAO;
import com.ss.task.dto.NewsInfo;
import com.ss.task.model.NewsEntity;
import com.ss.task.util.ErrorInfo;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by liymm on 2015-04-17.
 */
@Service
public class NewsService {

    @Autowired
    NewsDAO newsDAO;

    public BaseResult addNews(NewsEntity news) {

        NewsEntity n = new NewsEntity();

        n.setTitle(news.getTitle());
        n.setContent(news.getContent());
        n.setPubtime(new Date());

        newsDAO.save(n);

        return new BaseResult();
    }

    public ResultModel<List<NewsInfo>> getNewsList(Integer offset, Integer length) {
        return new ResultModel(newsDAO.getNewsList(offset, length));
    }

    public ResultModel<Long> getCountOfNews() {
        return new ResultModel(newsDAO.getTotalCount());
    }

    public ResultModel<NewsEntity> viewNews(Integer id) {
        NewsEntity n = newsDAO.load(id);
        if (n == null)
            return new ResultModel<NewsEntity>(ErrorInfo.INVALID_PARAMETER);

        return new ResultModel(n);
    }

    public BaseResult removeNews(Integer id) {
        newsDAO.delete(id);
        return new BaseResult();
    }

    public BaseResult editNews(Integer id, String title, String content) {

        NewsEntity news = newsDAO.load(id);
        if (news == null)
            return new BaseResult(ErrorInfo.INVALID_PARAMETER);

        news.setTitle(title);
        news.setContent(content);

        newsDAO.update(news);

        return new BaseResult();
    }

}
