package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryAddDTO implements Serializable {
    private String name;

    private String description;

    private Integer status;

}
