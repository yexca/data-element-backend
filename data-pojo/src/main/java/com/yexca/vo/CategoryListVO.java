package com.yexca.vo;

import lombok.Data;
import java.io.Serializable;
@Data
public class CategoryListVO implements Serializable {
    private Long categoryId;

    private String name;

    private Integer status;
}
