package com.ss.webutil.baidu.common;

/**
 * Created by LiYm on 2015/7/15.
 */
public class GeoLocation {
    Double lng;
    Double lat;

    public GeoLocation() {
    }

    public GeoLocation(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String toString(){
        return "lng = "+lng+",lat = "+lat;
    }

}
