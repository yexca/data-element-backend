package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.EnterpriseDataPageQueryDTO;
import com.yexca.entity.EnterpriseData;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EnterpriseDataMapper {

    void insert(EnterpriseData enterpriseData);

    @Delete("delete from enterprise_data where data_id = #{id}")
    void deleteById(Long id);

    Page<EnterpriseData> pageQuery(EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO);

    void update(EnterpriseData enterpriseData);

    @Select("select * from enterprise_data where data_id = #{id}")
    EnterpriseData getById(Long id);

    @Select("select * from enterprise_data where category_id = #{id}")
    List<EnterpriseData> getByCategoryId(Long id);
}
