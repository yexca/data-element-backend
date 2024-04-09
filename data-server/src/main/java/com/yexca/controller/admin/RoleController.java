package com.yexca.controller.admin;

import com.yexca.result.Result;
import com.yexca.service.RoleService;
import com.yexca.vo.RoleListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
@Slf4j
@Api(tags = "角色相关接口")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 获取全部角色
     * @return
     */
    @GetMapping("/list")
    public Result<List<RoleListVO>> list(){
        log.info("获取全部角色");
        List<RoleListVO> roleListVO = roleService.list();
        return Result.success(roleListVO);
    }
}
