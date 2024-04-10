package com.yexca.service;

import com.yexca.dto.EnterpriseUserAddDTO;
import com.yexca.dto.EnterpriseUserPageQueryDTO;
import com.yexca.dto.EnterpriseUserUpdateDTO;
import com.yexca.result.PageResult;
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
}