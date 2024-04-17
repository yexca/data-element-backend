package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class EnterpriseUserRegisterDTO implements Serializable {
    private String username;

    private String password;

    private String enterpriseName;

    private String email;

    private String phone;

    private Long countryId;

    private Integer roleId;

    private Integer status;

    private String evidence;
}
