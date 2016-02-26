package com.mai.activity.entity;

import java.io.Serializable;

/**
 * Created by denghao on 15/10/5.
 */
public class ActivitytagRef implements Serializable {

    private static final long serialVersionUID = -1832130109674459445L;

    private String tagActivityRefID;
    private String activityID;
    private String tagID;

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getTagActivityRefID() {
        return tagActivityRefID;
    }

    public void setTagActivityRefID(String tagActivityRefID) {
        this.tagActivityRefID = tagActivityRefID;
    }

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }
}
