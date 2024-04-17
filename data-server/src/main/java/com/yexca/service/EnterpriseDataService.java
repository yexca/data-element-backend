package com.yexca.service;

import com.yexca.dto.EnterpriseDataAddDTO;
import com.yexca.dto.EnterpriseDataPageQueryDTO;
import com.yexca.dto.EnterpriseDataUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.vo.EnterpriseDataUpdateVO;

public interface EnterpriseDataService {
    /**
     * 新增企业用户数据
     * @param enterpriseDataAddDTO
     */
    void add(EnterpriseDataAddDTO enterpriseDataAddDTO, String from);

    /**
     * 通过ID删除企业数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 企业数据分页查询
     * @param enterpriseDataPageQueryDTO
     * @return
     */
    PageResult pageQuery(EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO);

    /**
     * 更新企业数据
     * @param id
     * @param enterpriseDataUpdateDTO
     */
    void update(Long id, EnterpriseDataUpdateDTO enterpriseDataUpdateDTO, String from);

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    EnterpriseDataUpdateVO getById(Long id);

    /**
     * 用户端分页查询
     * @param currentUserId
     * @param enterpriseDataPageQueryDTO
     * @return
     */
    PageResult userPageQuery(Long currentUserId, EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO);
}
