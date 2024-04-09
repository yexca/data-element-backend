package com.yexca.service;

import com.yexca.vo.RoleListVO;

import java.util.List;

public interface RoleService {
    /**
     * 获取全部角色
     * @return
     */
    List<RoleListVO> list();
}
