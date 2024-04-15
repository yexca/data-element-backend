package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.PersonalUserPageQueryDTO;
import com.yexca.entity.PersonalUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PersonalUserMapper {
    @Insert("insert into personal_user(username, password, nickname, email, phone, gender, country_id, nin, status, wx_openid, create_by, create_from, create_time, update_by, update_from, update_time) VALUES " +
            "(#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{gender}, #{countryId}, #{nin}, #{status}, #{wxOpenid}, #{createBy}, #{createFrom}, #{createTime}, #{updateBy}, #{updateFrom}, #{updateTime})")
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
}
