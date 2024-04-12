package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CategoryPageQueryDTO implements Serializable {
    private Long categoryId;

    private String name;

    private String description;

    private Integer status;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
