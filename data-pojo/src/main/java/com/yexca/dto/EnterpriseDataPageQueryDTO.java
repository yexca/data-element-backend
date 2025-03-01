package com.yexca.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class EnterpriseDataPageQueryDTO implements Serializable {
    private Long dataId;

    private Long userId;

    private String name;

    private String description;

    private Long categoryId;

    private String sampleFileLink;

    private String fileLink;

    private Integer status;

    // 页码
    private int page;

    // 每页显示记录数
    private int pageSize;
}
