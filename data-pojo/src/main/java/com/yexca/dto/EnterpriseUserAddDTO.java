package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class EnterpriseUserAddDTO implements Serializable {
    private String username;

    private String password;

    private String enterpriseName;

    private String email;

    private Integer phone;

    private Integer countryId;

    private Integer status;

    private String evidence;
}
