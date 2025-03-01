package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.PersonalUserPageQueryDTO;
import com.yexca.entity.PersonalUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonalUserMapper {

    void insert(PersonalUser personalUser);

    @Delete("delete from personal_user where user_id = #{id}")
    void deleteById(Long id);

    Page<PersonalUser> pageQuery(PersonalUserPageQueryDTO personalUserPageQueryDTO);

    void update(PersonalUser personalUser);

    @Select("select * from personal_user where user_id = #{id}")
    PersonalUser getById(Long id);

    @Select("select username from personal_user where user_id = #{id}")
    String getUsernameById(Long id);

    @Select("select * from personal_user where username = #{username}")
    PersonalUser getByUsername(String username);

    @Select("select nickname from personal_user where user_id = #{id}")
    String getNicknameById(Long id);

    @Select("select * from personal_user where country_id = #{id}")
    List<PersonalUser> getByCountryId(Long id);
}
