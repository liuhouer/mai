package com.mai.sensitiveword.entity;

import java.io.Serializable;

/**
 * Created by sks on 2015/12/8.
 */
public class SensitiveWord implements Serializable {
    private static final long serialVersionUID = 5429051275938813503L;
    private String sensitiveWordID;
    private String content;//敏感词内容
    private Integer isEnable;//是否启用
    private Integer sensitiveLevel;//敏感级别

    public String getSensitiveWordID() {
        return sensitiveWordID;
    }

    public void setSensitiveWordID(String sensitiveWordID) {
        this.sensitiveWordID = sensitiveWordID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Integer getSensitiveLevel() {
        return sensitiveLevel;
    }

    public void setSensitiveLevel(Integer sensitiveLevel) {
        this.sensitiveLevel = sensitiveLevel;
    }
}

