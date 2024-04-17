package com.yexca.service;

import com.yexca.dto.*;
import com.yexca.entity.PersonalUser;
import com.yexca.result.PageResult;
import com.yexca.vo.PersonalUserLoginVO;
import com.yexca.vo.PersonalUserPageQueryVO;
import com.yexca.vo.PersonalUserUpdateVO;

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

    /**
     * 通过ID获取用户信息
     * @param id
     * @return
     */
    PersonalUserUpdateVO getById(Long id);

    /**
     * 用户端获取信息
     * @param id
     * @return
     */
    PersonalUserPageQueryVO getInfo(Long id);

    /**
     * 用户登录
     * @param personalLoginDTO
     * @return
     */
    PersonalUser login(PersonalLoginDTO personalLoginDTO);

    /**
     * 个人用户注册后登录
     * @param personalUserRegisterDTO
     * @return
     */
    PersonalUser register(PersonalUserRegisterDTO personalUserRegisterDTO);
}
