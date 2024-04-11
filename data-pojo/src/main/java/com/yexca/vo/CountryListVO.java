package com.yexca.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long countryId;

    private Integer phone;

    private String name;

    private Integer status;
}
