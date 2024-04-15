package com.yexca.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class PersonalUserLoginVO implements Serializable {
    private Long userId;

    private String username;

    private String nickname;

    private Integer role;

    private String token;
}
