package com.yexca.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ESData implements Serializable {
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
/*
PUT /data-element
{
  "mappings": {
    "properties": {
      "dataId": {
        "type": "long",
        "index": false
      },
      "userId": {
        "type": "long",
        "index": false
      },
      "username": {
        "type": "text",
        "index": true
      },
      "userRole": {
        "type": "integer",
        "index": false
      },
      "name": {
        "type": "text",
        "analyzer": "ik_max_word",
        "copy_to": "all"
      },
      "description": {
        "type": "text",
        "analyzer": "ik_max_word",
        "copy_to": "all"
      },
      "categoryId": {
        "type": "long",
        "index": false
      },
      "categoryName": {
        "type": "text",
        "analyzer": "ik_max_word",
        "copy_to": "all"
      },
      "sampleFileLink": {
        "type": "text",
        "index": false
      },
      "fileLink": {
        "type": "text",
        "index": false
      },
      "createTime": {
        "type": "date",
        "index": false
      },
      "updateTime": {
        "type": "date",
        "index": false
      },
      "all": {
        "type": "text",
        "analyzer": "ik_max_word"
      }
    }
  }
}
* */