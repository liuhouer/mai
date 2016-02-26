package com.mai.city.entity;

import java.io.Serializable;

/**
 * 城市
 * Created by fengdzh on 2015/9/14.
 */
public class City implements Serializable {
    private static final long serialVersionUID = -6820197813599623788L;
    private String cityID;
    private String cityName;

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
