package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.EnterpriseUserPageQueryDTO;
import com.yexca.entity.EnterpriseUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EnterpriseUserMapper {
    @Insert("insert into enterprise_user(username, password, enterprise_name, email, phone, country_id, status, evidence, create_by, create_from, create_time, update_by, update_from, update_time) VALUES " +
            "(#{username}, #{password}, #{enterpriseName}, #{email}, #{phone}, #{countryId},#{status}, #{evidence}, #{createBy}, #{createFrom}, #{createTime}, #{updateBy}, #{updateFrom}, #{updateTime})")
    void insert(EnterpriseUser enterpriseUser);

    @Delete("delete from enterprise_user where user_id = #{id}")
    void deleteById(Long id);

    Page<EnterpriseUser> pageQuery(EnterpriseUserPageQueryDTO enterpriseUserPageQueryDTO);

    @Select("select * from enterprise_user where user_id = #{id}")
    EnterpriseUser getById(Long id);

    void update(EnterpriseUser enterpriseUser);

    @Select("select username from enterprise_user where user_id = #{userId}")
    String getUsernameById(Long userId);

    @Select("select * from enterprise_user where username = #{username}")
    EnterpriseUser getByUsername(String username);
}
