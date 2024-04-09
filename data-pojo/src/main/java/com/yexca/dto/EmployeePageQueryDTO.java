package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {
    // 员工用户名
    private String username;
    // 页码
    private int page;
    // 每页显示记录数
    private int pageSize;
}
