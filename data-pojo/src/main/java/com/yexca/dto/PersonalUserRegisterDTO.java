package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonalUserRegisterDTO implements Serializable {
    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private Integer gender;

    private Long countryId;

    private String nin;

    private Integer roleId;

    private Integer status;
}
