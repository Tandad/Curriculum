package com.ss.task.rest;


import com.ss.task.dto.NewsInfo;
import com.ss.task.model.NewsEntity;
import com.ss.task.service.NewsService;
import com.ss.webutil.struct.BaseResult;
import com.ss.webutil.struct.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liymm on 2015-04-17.
 */
@RestController
@RequestMapping("/rest/news/")
public class NewsController {

    @Autowired
    NewsService newsService;

    @RequestMapping("add")
    public BaseResult addNews(NewsEntity news) {
        return newsService.addNews(news);
    }

    @RequestMapping("list")
    public ResultModel<List<NewsInfo>> listNews(Integer offset, Integer length) {
        return newsService.getNewsList(offset, length);
    }

    @RequestMapping("edit")
    public BaseResult editNews(Integer id, String title, String content) {
        return newsService.editNews(id, title, content);
    }

    @RequestMapping("remove")
    public BaseResult removeNews(Integer id) {
        return newsService.removeNews(id);
    }


    @RequestMapping("list/count")
    public ResultModel<Long> countNews() {
        return newsService.getCountOfNews();
    }

}