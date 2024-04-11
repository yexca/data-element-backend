package com.yexca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class PersonalUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private Integer gender;

    private Long countryId;

    private String nin;

    private Integer status;

    private String wxOpenid;

    private Long createBy;

    private String createFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateBy;

    private String UpdateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private String ext1;

    private String ext2;

    private String ext3;
}
