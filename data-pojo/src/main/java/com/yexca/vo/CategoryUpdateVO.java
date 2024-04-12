package com.yexca.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryUpdateVO implements Serializable {
    private Long categoryId;

    private String name;

    private String description;

    private Integer status;
}
