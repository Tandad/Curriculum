package com.ss.webutil.baidu.lbs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.webutil.wechat.pay.KVPair;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by LiYm on 2015/8/4.
 */
public abstract class LBSBase {

    protected String ak;
    protected String geotable_id;
    protected Integer coord_type;

    protected Integer id;
    protected String title;
    protected String tags;
    protected String address;
    protected Double latitude;
    protected Double longitude;
    protected Double[] location = new Double[2];

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getGeotable_id() {
        return geotable_id;
    }

    public void setGeotable_id(String geotable_id) {
        this.geotable_id = geotable_id;
    }

    public Integer getCoord_type() {
        return coord_type;
    }

    public void setCoord_type(Integer coord_type) {
        this.coord_type = coord_type;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toForm() {

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        Class<?> persistentClass = this.getClass();

        System.out.println(persistentClass);

        for (Class<?> cls = persistentClass; cls != Object.class; cls = cls.getSuperclass()) {
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                //不需要静态属性
                if ( (field.getModifiers() & Modifier.STATIC) != 0)
                    continue;

                try {
                    field.setAccessible(true);
                    Object value = field.get(this);
                    if (value == null)
                        continue;

                    if (first) {
                        first = false;
                    } else {
                        sb.append("&");
                    }

                    sb.append(field.getName()).append("=").append(value);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

    public String toJson(ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
