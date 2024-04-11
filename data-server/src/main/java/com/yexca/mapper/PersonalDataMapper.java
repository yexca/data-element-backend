package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.PersonalDataPageQueryDTO;
import com.yexca.entity.PersonalData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonalDataMapper {
    @Insert("insert into personal_data(user_id, name, description, category_id, file_link, status, create_by, create_from, create_time, update_by, update_from, update_time) VALUES " +
            "(#{userId}, #{name}, #{description}, #{categoryId}, #{fileLink}, #{status}, #{createBy}, #{createFrom}, #{createTime}, ${updateBy}, #{updateFrom}, #{updateTime})")
    void insert(PersonalData personalData);

    @Delete("delete from personal_data where data_id = #{id}")
    void deleteById(Long id);

    Page<PersonalData> pageQuery(PersonalDataPageQueryDTO personalDataPageQueryDTO);

    void update(PersonalData personalData);

    @Select("select * from personal_data where data_id = #{id}")
    PersonalData getById(Long id);
}
