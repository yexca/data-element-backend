package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeUpdateDTO implements Serializable {
    private Long countryId;

    private String email;

    private Integer gender;

    private String nickname;

    private String nin;

    private String password;

    private Long phone;

    private Integer roleId;

    private Integer status;

    private String username;
}
