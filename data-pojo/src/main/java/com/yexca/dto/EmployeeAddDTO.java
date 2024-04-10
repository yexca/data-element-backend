package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeAddDTO implements Serializable {
    private Integer countryId;
    private String email;
    private Integer gender;
    private String nickname;
    private String nin;
    private String password;
    private Integer phone;
    private Integer roleId;
    private String username;
}
