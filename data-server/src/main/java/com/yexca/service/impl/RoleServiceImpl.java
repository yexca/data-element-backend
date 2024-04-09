package com.yexca.service.impl;

import com.yexca.mapper.RoleMapper;
import com.yexca.service.RoleService;
import com.yexca.vo.RoleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    /**
     * 获取全部角色
     * @return
     */
    @Override
    public List<RoleListVO> list() {
        return roleMapper.list();
    }
}
