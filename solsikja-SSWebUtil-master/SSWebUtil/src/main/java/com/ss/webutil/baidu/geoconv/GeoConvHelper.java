package com.ss.webutil.baidu.geoconv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.baidu.BaiduConfig;
import com.ss.webutil.baidu.common.GeoLocation;
import com.ss.webutil.net.Http;

/**
 * Created by admin on 2015/7/21.
 */
public class GeoConvHelper {

    //源坐标类型
    public enum From{
        gps(1),
        sogou(2),
        google(3),
        soso(3),
        aliyun(3),
        mapabc(3),
        amap(3),
        mi(4),
        baidu(5),
        baidumi(6),
        mapbar(7),
        is51(8);

        int value;
        From(int value){ this.value = value; }

        public int getValue(){
            return this.value;
        }

    }
    //目的坐标类型
    public enum To{
        bd09ll(5),
        bd09mc(6);

        int value;
        To(int value) { this.value = value; }

        public int getValue(){
            return this.value;
        }

    }

    public static GeoLocation convert(ObjectMapper objectMapper, BaiduConfig baiduConfig, From from,To to, GeoLocation location){
        StringBuilder url = new StringBuilder();
        url.append("http://api.map.baidu.com/geoconv/v1/?ak=").append(baiduConfig.getAk())
                .append("&coords=").append(location.getLng()).append(',').append(location.getLat())
                .append("&output=json&from=").append(from.getValue())
                .append("&to=").append(to.getValue());

        String resp = Http.get(url.toString());

        if (resp != null) {
            try {
                GeoConvResponse geoChengeResponse = objectMapper.readValue(resp, GeoConvResponse.class);
                location.setLng(Double.parseDouble(geoChengeResponse.getResult().get(0).getX()));
                location.setLat(Double.parseDouble(geoChengeResponse.getResult().get(0).getY()));
                return location;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;

    }

}
