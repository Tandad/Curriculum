package com.ss.webutil.baidu.lbs;

/**
 * Created by yons on 2015/4/23.
 */
public class LBSResponse {

    private Integer status;
    private Integer id;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
