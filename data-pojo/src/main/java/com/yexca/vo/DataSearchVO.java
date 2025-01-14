package com.yexca.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class DataSearchVO {
    private Long dataId;

    private Long userId;

    private String username;

    private Integer userRole;

    private String name;

    private String description;

    private Long categoryId;

    private String categoryName;

    private String sampleFileLink;

    private String fileLink;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
