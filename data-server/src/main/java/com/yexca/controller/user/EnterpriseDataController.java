package com.yexca.controller.user;

import com.yexca.constant.FromConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.EnterpriseDataAddDTO;
import com.yexca.dto.EnterpriseDataPageQueryDTO;
import com.yexca.dto.EnterpriseDataUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.EnterpriseDataService;
import com.yexca.vo.EnterpriseDataUpdateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userEnterpriseDataController")
@RequestMapping("/user/data/enterprise")
@Slf4j
@Api(tags = "企业数据相关接口")
public class EnterpriseDataController {
    @Autowired
    private EnterpriseDataService enterpriseDataService;

    /**
     * 新增企业用户数据
     * @param enterpriseDataAddDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody EnterpriseDataAddDTO enterpriseDataAddDTO){
        log.info("新增企业用户数据：{}", enterpriseDataAddDTO);
        Long currentUserId = BaseContext.getCurrentUserId();
        enterpriseDataAddDTO.setUserId(currentUserId);
        enterpriseDataService.add(enterpriseDataAddDTO, FromConstant.USER);
        return Result.success();
    }

    /**
     * 通过ID删除企业数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("删除企业数据ID：{}", id);
        enterpriseDataService.deleteById(id);
        return Result.success();
    }

    /**
     * 企业数据分页查询
     * @param enterpriseDataPageQueryDTO
     * @return
     */
    @GetMapping
    public Result<PageResult> userPageQuery(EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO){
        Long currentUserId = BaseContext.getCurrentUserId();
//        enterpriseDataPageQueryDTO.setUserId(currentUserId);
        log.info("企业ID：{}数据分页查询：{}",currentUserId, enterpriseDataPageQueryDTO);
        PageResult pageResult = enterpriseDataService.userPageQuery(currentUserId, enterpriseDataPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改企业数据
     * @param id
     * @param enterpriseDataUpdateDTO
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody EnterpriseDataUpdateDTO enterpriseDataUpdateDTO){
        log.info("修改企业数据ID：{}，信息：{}", id, enterpriseDataUpdateDTO);
        enterpriseDataService.update(id, enterpriseDataUpdateDTO, FromConstant.USER);
        return Result.success();
    }

    /**
     * 通过ID获取企业数据信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<EnterpriseDataUpdateVO> getById(@PathVariable Long id){
        log.info("获取企业数据ID：{}", id);
        EnterpriseDataUpdateVO enterpriseDataUpdateVO = enterpriseDataService.getById(id);
        return Result.success(enterpriseDataUpdateVO);
    }
}
