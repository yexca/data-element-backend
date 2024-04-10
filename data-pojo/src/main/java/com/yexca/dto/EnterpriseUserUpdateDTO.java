package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class EnterpriseUserUpdateDTO implements Serializable {
    private String username;

    private String password;

    private String enterpriseName;

    private String email;

    private String phone;

    private Integer countryId;

    private Integer status;

    private String evidence;
}
