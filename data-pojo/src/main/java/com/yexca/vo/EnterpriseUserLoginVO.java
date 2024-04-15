package com.yexca.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class EnterpriseUserLoginVO implements Serializable {
    private Long userId;

    private String username;

    private String enterpriseName;

    private Integer role;

    private String token;
}
