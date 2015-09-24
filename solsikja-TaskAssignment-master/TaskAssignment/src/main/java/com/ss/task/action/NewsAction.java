package com.ss.task.action;

import com.ss.task.action.auth.UserAuthPassport;
import com.ss.task.model.NewsEntity;
import com.ss.task.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liymm on 2015-04-17.
 */
@Controller
@RequestMapping("/news/")
@UserAuthPassport
public class NewsAction {

    @Autowired
    NewsService newsService;

    @RequestMapping("add")
    public String addNews() {
        return "news/add";
    }

    @RequestMapping("list")
    public String listNews() {
        return "news/list";
    }

    @RequestMapping("{id}")
    public String getDetail(@PathVariable Integer id, ModelMap model) {

        NewsEntity news = newsService.viewNews(id).getData();

        model.addAttribute("news", news);

        return "news/detail";
    }

    @RequestMapping("{id}/edit")
    public String editNews(@PathVariable Integer id, ModelMap model) {

        NewsEntity news = newsService.viewNews(id).getData();

        model.addAttribute("id", news.getId());
        model.addAttribute("title", news.getTitle());
        model.addAttribute("content", news.getContent());

        return "news/edit";
    }

}
