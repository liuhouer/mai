package com.mai.activity.dto;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/9/10.
 */
public class ActivityDTO implements Serializable {
    public static final String ORDERRULE_TIME = "1";
    public static final String ORDERRULE_HOT = "2";
    public static final String ORDERRULE_DISTANCE = "3";
    private static final long serialVersionUID = 2124908228078840461L;
    //gps坐标位置
    String gpsLatitude;
    String gpsLongitude;
    //排序规则
    private String orderRule;
    //活动分类ID
    private String categoryID;
    //tagID
    private String tagID;
    //开始时间
    private Long startTime;
    //截至时间
    private Long endTime;
    //区域过滤:我的学校：1 是我的学校
    private String schoolID;
    //按照区域：
    private String locationID;
    private String personID;


    public ActivityDTO() {
    }

    public ActivityDTO(String orderRule, String categoryID, String tagID, Long startTime,
                       Long endTime, String schoolID, String locationID, String gpsLatitude, String gpsLongitude, String personID) {
        this.setOrderRule(orderRule);
        this.setTagID(tagID);
        this.setCategoryID(categoryID);
        this.setLocationID(locationID);
        this.setEndTime(endTime);
        this.setStartTime(startTime);
        this.setSchoolID(schoolID);
        this.setGpsLongitude(gpsLongitude);
        this.setGpsLatitude(gpsLatitude);
        this.setPersonID(personID);
    }

    public static String getOrderruleDistance() {
        return ORDERRULE_DISTANCE;
    }

    public static String getOrderruleHot() {
        return ORDERRULE_HOT;
    }

    public static String getOrderruleTime() {
        return ORDERRULE_TIME;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getOrderRule() {
        return orderRule;
    }

    public void setOrderRule(String orderRule) {
        this.orderRule = orderRule;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
}
