package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.CountryPageQueryDTO;
import com.yexca.entity.Country;
import com.yexca.vo.CountryListVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CountryMapper {

    @Select("select name from country where country_id = #{countryId}")
    String getNameByCountryId(Long countryId);

    @Select("select * from country")
    List<CountryListVO> list();

    @Select("select phone from country where country_id = #{countryId}")
    Integer getPhoneById(Long countryId);

    @Insert("insert into country(phone, name, status, create_by, create_time, update_by, update_time) VALUES " +
            "(${phone}, #{name}, #{status}, #{createBy}, #{createTime}, #{updateBy}, #{updateTime})")
    void insert(Country country);

    @Delete("delete from country where country_id = #{id}")
    void deleteById(Long id);

    Page<Country> pageQuery(CountryPageQueryDTO countryPageQueryDTO);

    void update(Country country);

    @Select("select * from country where country_id = #{id}")
    Country getById(Long id);
}
