package com.ss.webutil.baidu.geocoding;

import com.ss.webutil.baidu.common.GeoLocation;

/**
 * Created by LiYm on 2015/7/15.
 */
public class GeoDecodingResponse {

    Integer status;
    String location;
    Result result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        GeoLocation location;
        String formatted_address;
        String business;
        AddressComponent addressComponent;

        public Result() {}

        public GeoLocation getLocation() {
            return location;
        }

        public void setLocation(GeoLocation location) {
            this.location = location;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public AddressComponent getAddressComponent() {
            return addressComponent;
        }

        public void setAddressComponent(AddressComponent addressComponent) {
            this.addressComponent = addressComponent;
        }
    }

    public static class AddressComponent {
        String country;
        String province;
        String city;
        String district;
        String street;
        String street_number;
        Integer country_code;
        String direction;
        Double distance;

        public AddressComponent() {
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreet_number() {
            return street_number;
        }

        public void setStreet_number(String street_number) {
            this.street_number = street_number;
        }

        public Integer getCountry_code() {
            return country_code;
        }

        public void setCountry_code(Integer country_code) {
            this.country_code = country_code;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public Double getDistance() {
            return distance;
        }

        public void setDistance(Double distance) {
            this.distance = distance;
        }
    }
}
