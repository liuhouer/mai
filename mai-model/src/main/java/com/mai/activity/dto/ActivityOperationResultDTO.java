package com.mai.activity.dto;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/10/5.
 */
public class ActivityOperationResultDTO implements Serializable {
    private static final long serialVersionUID = 5702399588667982619L;
    private Integer code;
    private String message;
    private ActivityOperationDTO activityOperationDTO;

    public ActivityOperationDTO getActivityOperationDTO() {
        return activityOperationDTO;
    }

    public void setActivityOperationDTO(ActivityOperationDTO activityOperationDTO) {
        this.activityOperationDTO = activityOperationDTO;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
