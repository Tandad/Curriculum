package com.ss.webutil.baidu.geocoding;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.baidu.BaiduConfig;
import com.ss.webutil.baidu.common.GeoLocation;
import com.ss.webutil.net.Http;

import java.io.IOException;

/**
 * Created by LiYm on 2015/7/15.
 */
public class GeoHelper {

    public enum CoordType {
        bd09ll("bd09ll"),
        gcj02ll("gcj02ll"),
        wgs84ll("wgs84ll");

        String value;

        CoordType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static GeoDecodingResponse decoding(ObjectMapper objectMapper, BaiduConfig baiduConfig, CoordType coordType, GeoLocation location) {
        StringBuilder url = new StringBuilder();
        url.append("http://api.map.baidu.com/geocoder/v2/?ak=").append(baiduConfig.getAk())
                .append("&location=").append(location.getLat()).append(',').append(location.getLng())
                .append("&output=json&pois=0&coordtype=").append(coordType.getValue());

        String resp = Http.get(url.toString());

        if (resp != null) {
            try {
                return objectMapper.readValue(resp, GeoDecodingResponse.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        GeoDecodingResponse r = new GeoDecodingResponse();
        r.setStatus(-1);
        return r;
    }
}
