package com.mai.activity.dto;

import com.mai.activity.entity.PhotoTag;

import java.io.Serializable;

/**
 * Created by denghao on 15/12/10.
 */
public class PhotoTagDTO implements Serializable {
    private static final long serialVersionUID = 7557570809499931890L;

    private Integer code;
    private PhotoTag photoTag;
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

    public PhotoTag getPhotoTag() {
        return photoTag;
    }

    public void setPhotoTag(PhotoTag photoTag) {
        this.photoTag = photoTag;
    }
}
