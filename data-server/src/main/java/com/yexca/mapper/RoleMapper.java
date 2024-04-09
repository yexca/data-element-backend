package com.yexca.mapper;

import com.yexca.vo.RoleListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("select name from role where role_id = #{RoleId}")
    String getNameByRoleId(Integer RoleId);

    /**
     * 获取全部角色
     * @return
     */
    @Select("select * from role")
    List<RoleListVO> list();
}
