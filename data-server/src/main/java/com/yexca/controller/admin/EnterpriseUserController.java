package com.yexca.controller.admin;

import com.yexca.dto.EnterpriseUserAddDTO;
import com.yexca.dto.EnterpriseUserPageQueryDTO;
import com.yexca.dto.EnterpriseUserUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.EnterpriseUserService;
import com.yexca.vo.EnterpriseUserPageQueryVO;
import com.yexca.vo.EnterpriseUserUpdateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminEnterpriseUserController")
@RequestMapping("/admin/users/enterprise")
@Slf4j
@Api(tags = "企业用户相关接口")
public class EnterpriseUserController {
    @Autowired
    private EnterpriseUserService enterpriseUserService;

    /**
     * 添加企业用户
     * @param enterpriseUserAddDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody EnterpriseUserAddDTO enterpriseUserAddDTO){
        log.info("添加企业用户：{}", enterpriseUserAddDTO);
        enterpriseUserService.add(enterpriseUserAddDTO);
        return Result.success();
    }

    /**
     * 删除企业用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除企业用户：{}", id);
        enterpriseUserService.deleteById(id);
        return Result.success();
    }

    /**
     * 企业用户分页查询
     * @param enterpriseUserPageQueryDTO
     * @return
     */
    @GetMapping
    public Result<PageResult> pageQuery(EnterpriseUserPageQueryDTO enterpriseUserPageQueryDTO){
        log.info("企业用户分页查询：{}", enterpriseUserPageQueryDTO);
        PageResult pageResult = enterpriseUserService.pageQuery(enterpriseUserPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<EnterpriseUserUpdateVO> getById(@PathVariable Long id){
        log.info("获取企业用户：{}", id);
        EnterpriseUserUpdateVO enterpriseUserUpdateVO = enterpriseUserService.getById(id);
        return Result.success(enterpriseUserUpdateVO);
    }

    /**
     * 修改企业用户
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody EnterpriseUserUpdateDTO enterpriseUserUpdateDTO){
        log.info("修改企业用户ID：{}，信息：{}", id, enterpriseUserUpdateDTO);
        enterpriseUserService.update(id, enterpriseUserUpdateDTO);
        return Result.success();
    }
}
