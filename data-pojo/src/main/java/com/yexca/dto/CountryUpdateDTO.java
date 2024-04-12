package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CountryUpdateDTO implements Serializable {
    private Integer phone;

    private String name;

    private Integer status;
}
