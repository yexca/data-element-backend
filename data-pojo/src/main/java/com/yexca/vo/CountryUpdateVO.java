package com.yexca.vo;

import lombok.Data;

import java.io.Serializable;
@Data
public class CountryUpdateVO implements Serializable {
    private Long countryId;

    private Integer phone;

    private String name;

    private Integer status;
}
