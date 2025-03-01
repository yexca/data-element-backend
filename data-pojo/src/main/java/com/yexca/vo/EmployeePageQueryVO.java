package com.yexca.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmployeePageQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long EmployeeId;

    private String username;

    private String nickname;

    private String email;

    private String phone;

    private String gender;

    private String countryName;

    private String nin;

    private String status;

    private String roleName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long createBy;

    private Long updateBy;
}
