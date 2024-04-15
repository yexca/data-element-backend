package com.yexca.service;

import com.yexca.result.PageResult;

import java.io.IOException;

public interface SearchService {
    /**
     * 搜索
     * @param kw
     * @return
     */
    PageResult search(String kw) ;
}
