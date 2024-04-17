package com.yexca.service;

import com.yexca.dto.*;
import com.yexca.entity.EnterpriseUser;
import com.yexca.result.PageResult;
import com.yexca.vo.EnterpriseDataPageQueryVO;
import com.yexca.vo.EnterpriseUserPageQueryVO;
import com.yexca.vo.EnterpriseUserUpdateVO;

public interface EnterpriseUserService {
    /**
     * 添加企业用户
     * @param enterpriseUserAddDTO
     */
    void add(EnterpriseUserAddDTO enterpriseUserAddDTO);

    /**
     * 删除企业用户
     * @param id
     */
    void deleteById(Long id);

    /**
     * 企业用户分页查询
     * @param enterpriseUserPageQueryDTO
     * @return
     */
    PageResult pageQuery(EnterpriseUserPageQueryDTO enterpriseUserPageQueryDTO);

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    EnterpriseUserUpdateVO getById(Long id);

    /**
     * 修改企业用户
     * @param id
     * @param enterpriseUserUpdateDTO
     */
    void update(Long id, EnterpriseUserUpdateDTO enterpriseUserUpdateDTO);

    /**
     * 企业用户登录
     * @param enterpriseUserLoginDTO
     * @return
     */
    EnterpriseUser login(EnterpriseUserLoginDTO enterpriseUserLoginDTO);

    /**
     * 用户端获取信息
     * @param currentUserId
     * @return
     */
    EnterpriseUserPageQueryVO getInfo(Long currentUserId);

    /**
     * 企业用户注册
     * @param enterpriseUserRegisterDTO
     * @return
     */
    EnterpriseUser register(EnterpriseUserRegisterDTO enterpriseUserRegisterDTO);
}
