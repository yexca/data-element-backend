package com.yexca.service;

import com.yexca.dto.PersonalDataAddDTO;
import com.yexca.dto.PersonalDataPageQueryDTO;
import com.yexca.dto.PersonalDataUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.vo.PersonalDataUpdateVO;

public interface PersonalDataService {
    /**
     * 增加个人数据
     * @param personalDataAddDTO
     */
    void add(PersonalDataAddDTO personalDataAddDTO);

    /**
     * 根据ID删除数据
     * @param id
     */
    void deleteById(Long id);

    /**
     * 个人数据分页查询
     * @param personalDataPageQueryDTO
     * @return
     */
    PageResult pageQuery(PersonalDataPageQueryDTO personalDataPageQueryDTO);

    /**
     * 修改个人数据
     * @param id
     * @param personalDataUpdateDTO
     */
    void update(Long id, PersonalDataUpdateDTO personalDataUpdateDTO);

    /**
     * 根据Id获取个人数据信息
     * @param id
     * @return
     */
    PersonalDataUpdateVO getById(Long id);
}
