package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class PersonalUserPageQueryDTO implements Serializable {
    // 个人用户名
    private String username;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
