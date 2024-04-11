package com.yexca.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
    @Select("select name from category where category_id = #{id}")
    String getNameById(Long id);
}
