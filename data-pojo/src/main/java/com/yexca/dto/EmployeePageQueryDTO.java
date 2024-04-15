package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {
    // 员工ID
    private Long employeeId;

    // 员工用户名
    private String username;

    // 员工昵称
    private String nickname;

    // 员工邮箱
    private String email;

    private String phone;

    private Integer gender;

    private Long countryId;

    private String nin;

    private Integer status;

    private Integer roleId;

    private Integer currentRoleId;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
