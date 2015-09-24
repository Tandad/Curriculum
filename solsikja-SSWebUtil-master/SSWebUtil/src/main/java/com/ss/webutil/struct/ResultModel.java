package com.ss.webutil.struct;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by liymm on 2015-01-21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel<T> extends BaseResult {

    T data;

    public ResultModel() {
        super();
    }

    public ResultModel(T data) {
        setSuccData(data);
    }

    public ResultModel(String errorInfo) {
        setErrorInfo(errorInfo);
    }

    public T getData() {
        return data;
    }

    public ResultModel<T> setData(T data) {
        this.data = data;
        return this;
    }

    protected ResultModel<T> setSuccData(T data) {
        return ((ResultModel<T>)setStatus(SUCCESS)).setData(data);
    }
}
