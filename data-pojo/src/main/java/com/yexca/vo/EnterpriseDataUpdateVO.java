package com.yexca.vo;

import lombok.Data;
import java.io.Serializable;

@Data
public class EnterpriseDataUpdateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;

    private String username;

    private String name;

    private String description;

    private Long categoryId;

    private String sampleFileLink;

    private String fileLink;

    private Integer status;
}
