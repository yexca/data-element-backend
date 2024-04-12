package com.yexca.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CountryAddDTO implements Serializable {
    private Integer phone;

    private String name;

    private Integer status;
}
