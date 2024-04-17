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
