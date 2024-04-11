package com.yexca.mapper;

import com.yexca.vo.CountryListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountryMapper {

    @Select("select name from country_info where country_id = #{countryId}")
    String getNameByCountryId(Long countryId);

    @Select("select * from country_info")
    List<CountryListVO> list();

    @Select("select phone from country_info where country_id = #{countryId}")
    Integer getPhoneById(Long countryId);
}
