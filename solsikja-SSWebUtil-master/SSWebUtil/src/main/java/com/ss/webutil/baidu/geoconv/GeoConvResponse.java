package com.ss.webutil.baidu.geoconv;

import com.ss.webutil.baidu.common.GeoLocation;

import java.util.List;

/**
 * Created by admin on 2015/7/21.
 */
public class GeoConvResponse {

    Integer status;
    List<Result> result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        String x;
        String y;

        public Result() {}
        public String getX(){
            return x;
        }
        public void setX(String x){
            this.x = x;
        }
        public String getY(){
            return y;
        }
        public void setY(String y){
            this.y = y;
        }
        public String toString(){
            return "x = "+x+", y = "+y;
        }

    }

}

