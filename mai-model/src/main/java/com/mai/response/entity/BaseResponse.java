package com.mai.response.entity;

import java.io.Serializable;

/**
 * 移动端 返回基础类:统一约定返值代码
 * Created by fengdzh on 2015/9/28.
 */
public class BaseResponse implements Serializable {


    //系统繁忙
    public static final Integer RESPONSECODE_SYSTEM_BUSY = -1;
    //成功
    public static final Integer RESPONSECODE_SUCCESS = 0;
    private static final long serialVersionUID = 8474552652199875423L;
    private Integer responseCode;

    private String responseText;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
