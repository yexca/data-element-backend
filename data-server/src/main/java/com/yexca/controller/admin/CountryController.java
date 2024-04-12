package com.yexca.controller.admin;

import com.yexca.dto.CountryAddDTO;
import com.yexca.dto.CountryPageQueryDTO;
import com.yexca.dto.CountryUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.CountryService;
import com.yexca.vo.CountryListVO;
import com.yexca.vo.CountryUpdateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/country")
@Slf4j
@Api(tags = "国家相关接口")
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     * 获取全部国家
     * @return
     */
    @GetMapping("/list")
    public Result<List<CountryListVO>> list(){
        log.info("获取全部国家");
        List<CountryListVO> countryListVO = countryService.list();
        return Result.success(countryListVO);
    }

    /**
     * 新增国家或地区
     * @param countryAddDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody CountryAddDTO countryAddDTO){
        log.info("新增国家：{}", countryAddDTO);
        countryService.add(countryAddDTO);
        return Result.success();
    }

    /**
     * 删除国家或地区
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("删除国家ID：{}", id);
        countryService.deleteById(id);
        return Result.success();
    }

    /**
     * 分页查询
     * @param countryPageQueryDTO
     * @return
     */
    @GetMapping
    public Result<PageResult> pageQuery(CountryPageQueryDTO countryPageQueryDTO){
        log.info("国家分页查询：{}", countryPageQueryDTO);
        PageResult pageResult = countryService.pageQuery(countryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改国家信息
     * @param id
     * @param countryUpdateDTO
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody CountryUpdateDTO countryUpdateDTO){
        log.info("修改国家信息ID：{}，信息：{}", id, countryUpdateDTO);
        countryService.update(id, countryUpdateDTO);
        return Result.success();
    }

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<CountryUpdateVO> getById(@PathVariable Long id){
        log.info("获取国家信息ID：{}", id);
        CountryUpdateVO countryUpdateVO = countryService.getById(id);
        return Result.success(countryUpdateVO);
    }
}
