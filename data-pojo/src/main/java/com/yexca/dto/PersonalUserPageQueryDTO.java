package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class PersonalUserPageQueryDTO implements Serializable {
    // 个人用户名
    private String username;

    // ID
    private Long userId;

    // 昵称
    private String nickname;

    // 邮箱
    private String email;

    // 手机号
    private String phone;

    // 性别
    private Integer gender;

    // 国家
    private Long countryId;

    // nin
    private String nin;

    // 状态
    private Integer status;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
