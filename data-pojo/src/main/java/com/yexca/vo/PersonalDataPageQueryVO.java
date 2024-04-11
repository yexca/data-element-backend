package com.yexca.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PersonalDataPageQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long dataId;

    private String userName;

    private Long userId;

    private String name;

    private String description;

    private String categoryName;

    private String fileLink;

    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
