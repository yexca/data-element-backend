package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class PersonalUserAddDTO implements Serializable {
    private String username;

    private String password;

    private String nickname;

    private String email;

    private Integer phone;

    private Integer gender;

    private Integer countryId;

    private String nin;

    private Integer status;

    private String createFrom;

}
