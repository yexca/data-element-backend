package com.yexca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EnterpriseUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String password;

    private String enterpriseName;

    private String email;

    private String phone;

    private Long countryId;

    private Integer roleId;

    private Integer status;

    private String evidence;

    private Long createBy;

    private String createFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateBy;

    private String updateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
