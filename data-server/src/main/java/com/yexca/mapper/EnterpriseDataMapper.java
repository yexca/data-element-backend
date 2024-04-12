package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.EnterpriseDataPageQueryDTO;
import com.yexca.entity.EnterpriseData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EnterpriseDataMapper {
    @Insert("insert into enterprise_data(user_id, name, description, category_id, sample_file_link, file_link, status, create_by, create_from, create_time, update_by, update_from, update_time) VALUES " +
            "(#{userId}, #{name}, #{description}, #{categoryId}, #{sampleFileLink}, #{fileLink}, #{status}, #{createBy}, #{createFrom}, #{createTime}, #{updateBy}, #{updateFrom}, #{updateTime})")
    void insert(EnterpriseData enterpriseData);

    @Delete("delete from enterprise_data where data_id = #{id}")
    void deleteById(Long id);

    Page<EnterpriseData> pageQuery(EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO);

    void update(EnterpriseData enterpriseData);

    @Select("select * from enterprise_data where data_id = #{id}")
    EnterpriseData getById(Long id);
}
