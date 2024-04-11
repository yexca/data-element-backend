package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class EnterpriseUserPageQueryDTO implements Serializable {
    // 企业ID
    private Long userId;

    // 企业用户名
    private String username;

    // 企业名称
    private String enterpriseName;

    private String email;

    private String phone;

    private Long countryId;

    private Integer roleId;

    private Integer status;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
