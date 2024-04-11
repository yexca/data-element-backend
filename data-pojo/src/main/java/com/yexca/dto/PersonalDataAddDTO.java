package com.yexca.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class PersonalDataAddDTO implements Serializable {
    private Long userId;

    private String name;

    private String description;

    private Long categoryId;

    private String fileLink;

    private Integer status;
}
