package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeAddDTO implements Serializable {
    private Long countryId;
    private String email;
    private Integer gender;
    private String nickname;
    private String nin;
    private String password;
    private Long phone;
    private Integer roleId;
    private String username;
}
