package com.yexca.service;

import com.yexca.dto.PersonalUserAddDTO;
import com.yexca.dto.PersonalUserPageQueryDTO;
import com.yexca.dto.PersonalUserUpdateDTO;
import com.yexca.result.PageResult;

public interface PersonalUserService {
    /**
     * 增加个人用户
     * @param personalUserAddDTO
     */
    void add(PersonalUserAddDTO personalUserAddDTO);

    /**
     * 删除用户
     * @param id
     */
    void deleteById(Long id);

    /**
     * 个人用户分页查询
     * @param personalUserPageQueryDTO
     * @return
     */
    PageResult pageQuery(PersonalUserPageQueryDTO personalUserPageQueryDTO);

    /**
     * 修改个人用户
     * @param id
     * @param personalUserUpdateDTO
     */
    void update(Long id, PersonalUserUpdateDTO personalUserUpdateDTO);
}
