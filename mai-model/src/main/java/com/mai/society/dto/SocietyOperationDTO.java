package com.mai.society.dto;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/10/5.
 */
public class SocietyOperationDTO implements Serializable {
    private static final long serialVersionUID = 1493468408608730262L;
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
