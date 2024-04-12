package com.yexca.service;

import com.yexca.dto.CountryAddDTO;
import com.yexca.dto.CountryPageQueryDTO;
import com.yexca.dto.CountryUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.vo.CountryListVO;
import com.yexca.vo.CountryUpdateVO;

import java.util.List;

public interface CountryService {
    /**
     * 获取全部国家
     * @return
     */
    List<CountryListVO> list();

    /**
     * 新增国家
     * @param countryAddDTO
     */
    void add(CountryAddDTO countryAddDTO);

    /**
     * 删除国家
     * @param id
     */
    void deleteById(Long id);

    /**
     * 分页查询
     * @param countryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CountryPageQueryDTO countryPageQueryDTO);

    /**
     * 修改国家信息
     * @param id
     * @param countryUpdateDTO
     */
    void update(Long id, CountryUpdateDTO countryUpdateDTO);

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    CountryUpdateVO getById(Long id);
}
