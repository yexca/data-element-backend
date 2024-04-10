package com.yexca.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class EnterpriseUserUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String enterpriseName;

    private String email;

    private String phone;

    private Integer countryId;

    private Integer status;

    private String evidence;
}
