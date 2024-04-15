package com.yexca.dto;

import lombok.Data;
import java.io.Serializable;
@Data
public class PersonalLoginDTO implements Serializable {
    private String username;

    private String password;
}
