package com.yexca.service;

import com.yexca.vo.CountryListVO;

import java.util.List;

public interface CountryService {
    /**
     * 获取全部国家
     * @return
     */
    List<CountryListVO> list();
}
