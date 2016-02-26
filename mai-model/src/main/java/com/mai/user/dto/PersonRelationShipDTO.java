package com.mai.user.dto;

import java.io.Serializable;

/**
 * Created by sks on 2015/10/16.
 */
public class PersonRelationShipDTO implements Serializable {

    private static final long serialVersionUID = -7965057630549438952L;
    private Integer code;
    private String message;

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
