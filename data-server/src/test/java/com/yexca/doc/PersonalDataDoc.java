package com.yexca.doc;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PersonalDataDoc implements Serializable {
    private Long dataId;

    private Long userId;

    private String username;

    private String name;

    private String description;

    private Long categoryId;

    private String categoryName;

    private String fileLink;

    private Integer status;

    private Integer type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
