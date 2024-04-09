package com.yexca.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Integer roleId;

    private String Name;
}
