package com.yexca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class EnterpriseData implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long dataId;

    private Long userId;

    private String name;

    private String description;

    private Long categoryId;

    private String sampleFileLink;

    private String fileLink;

    private Integer status;

    private Long createBy;

    private String createFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private Long updateBy;

    private String updateFrom;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
