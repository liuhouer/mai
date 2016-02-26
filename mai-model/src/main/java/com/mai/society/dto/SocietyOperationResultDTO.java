package com.mai.society.dto;

import com.mai.society.dto.SocietyOperationDTO;

import java.io.Serializable;

/**
 * Created by fengdzh on 2015/10/5.
 */
public class SocietyOperationResultDTO implements Serializable {
    private static final long serialVersionUID = 5702399588667982619L;
    private Integer code;
    private String message;
    private JoinSocietyOperationDTO joinSocietyOperationDTO;

    public JoinSocietyOperationDTO getJoinSocietyOperationDTO() {
        return joinSocietyOperationDTO;
    }

    public void setJoinSocietyOperationDTO(JoinSocietyOperationDTO joinSocietyOperationDTO) {
        this.joinSocietyOperationDTO = joinSocietyOperationDTO;
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
