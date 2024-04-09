package com.yexca.service.impl;

import com.yexca.mapper.CountryMapper;
import com.yexca.service.CountryService;
import com.yexca.vo.CountryListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryMapper countryMapper;
    /**
     * 获取全部国家
     * @return
     */
    @Override
    public List<CountryListVO> list() {
        return countryMapper.list();
    }
}
