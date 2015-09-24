package com.ss.task.dto;

import java.util.Date;

/**
 * Created by liymm on 2015-04-17.
 */
public class NewsInfo {

    Integer id;
    String title;
    Date pubtime;

    public NewsInfo() {
    }

    public NewsInfo(Integer id, String title, Date pubtime) {
        this.id = id;
        this.title = title;
        this.pubtime = pubtime;
    }

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

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }
}
