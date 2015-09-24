package com.ss.webutil.struct;

/**
 * Created by liymm on 2015-01-21.
 */
public class BaseResult {

    public static final int SUCCESS = 0;
    public static final int FAILED = -1;

    Integer status;
    String info;

    public BaseResult() {
        super();
        setStatus(SUCCESS);
    }

    public BaseResult(String errorInfo) {
        setErrorInfo(errorInfo);
    }

    public final Integer getStatus() {
        return status;
    }

    public final BaseResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public final String getInfo() {
        return info;
    }

    public final BaseResult setInfo(String info) {
        this.info = info;
        return this;
    }

    protected final BaseResult setErrorInfo(String info) {
        this.status = FAILED;
        this.info = info;
        return this;
    }

    public final boolean failed() {
        return status == FAILED;
    }
}
