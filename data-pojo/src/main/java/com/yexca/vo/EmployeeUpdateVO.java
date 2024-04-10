package com.yexca.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EmployeeUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long EmployeeId;

    private String username;

    private String nickname;

    private String email;

    private Integer phone;

    private Integer gender;

    private Integer countryId;

    private String nin;

    private String status;

    private Integer roleId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private Long createBy;

    private Long updateBy;
}
