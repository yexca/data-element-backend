package com.yexca.mapper;

import com.yexca.vo.CountryListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountryMapper {

    @Select("select name from country_info where country_id = #{CountryId}")
    String getNameByCountryId(Integer CountryId);

    @Select("select * from country_info")
    List<CountryListVO> list();
}
