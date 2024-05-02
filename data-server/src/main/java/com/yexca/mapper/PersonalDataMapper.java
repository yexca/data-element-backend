package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.PersonalDataPageQueryDTO;
import com.yexca.entity.PersonalData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonalDataMapper {

    void insert(PersonalData personalData);

    @Delete("delete from personal_data where data_id = #{id}")
    void deleteById(Long id);

    Page<PersonalData> pageQuery(PersonalDataPageQueryDTO personalDataPageQueryDTO);

    void update(PersonalData personalData);

    @Select("select * from personal_data where data_id = #{id}")
    PersonalData getById(Long id);

    @Select("select * from personal_data where category_id = #{id}")
    List<PersonalData> getByCategoryId(Long id);
}
