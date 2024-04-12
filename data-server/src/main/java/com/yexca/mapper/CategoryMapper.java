package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.CategoryPageQueryDTO;
import com.yexca.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CategoryMapper {
    @Select("select name from category where category_id = #{id}")
    String getNameById(Long id);

    @Insert("insert into category(name, description, status, create_by, create_time, update_time, update_by) VALUES " +
            "(#{name}, #{description}, #{status}, #{createBy}, #{createTime}, #{updateTime}, #{updateBy})")
    void insert(Category category);

    @Delete("delete from category where category_id = #{id}")
    void deleteById(Long id);

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    void update(Category category);

    @Select("select * from category where category_id = #{id}")
    Category getById(Long id);
}
