package com.yexca.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CountryListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer countryId;

    private String name;

    private Integer status;
}
