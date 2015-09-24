package com.ss.task.model;

import java.util.Date;

/**
 * Created by liymm on 2015-04-17.
 */
public class NewsEntity {
    private Integer id;
    private String title;
    private String content;
    private Date pubtime;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }
}
