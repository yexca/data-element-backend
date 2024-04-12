package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryUpdateDTO implements Serializable {
    private String name;

    private String description;

    private Integer status;
}
