package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class CountryPageQueryDTO implements Serializable {
    private Long countryId;

    private Integer phone;

    private String name;

    private Integer status;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
