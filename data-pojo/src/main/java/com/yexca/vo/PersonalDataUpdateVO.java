package com.yexca.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class PersonalDataUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long dataId;

    private Long userId;

    private String name;

    private String description;

    private Long categoryId;

    private String fileLink;

    private Integer status;

}
