package com.mai.society.dto;

import java.io.Serializable;

/**
 * 加入社团标志
 * Created by fengdzh on 2015/10/17.
 */
public class JoinSocietyOperationDTO implements Serializable {

    private static final long serialVersionUID = 4410948993560170718L;
    private Integer code;
    private String btnName;
    private String message;

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
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
