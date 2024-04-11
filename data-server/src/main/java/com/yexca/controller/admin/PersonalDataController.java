package com.yexca.controller.admin;

import com.yexca.dto.PersonalDataAddDTO;
import com.yexca.dto.PersonalDataPageQueryDTO;
import com.yexca.dto.PersonalDataUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.PersonalDataService;
import com.yexca.vo.PersonalDataPageQueryVO;
import com.yexca.vo.PersonalDataUpdateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/data/personal")
@Slf4j
@Api(tags = "个人数据相关接口")
public class PersonalDataController {
    @Autowired
    private PersonalDataService personalDataService;

    /**
     * 新增数据
     * @param personalDataAddDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody PersonalDataAddDTO personalDataAddDTO){
        log.info("增加个人数据：{}", personalDataAddDTO);
        personalDataService.add(personalDataAddDTO);
        return Result.success();
    }

    /**
     * 根据ID删除数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("删除个人数据ID：{}", id);
        personalDataService.deleteById(id);
        return Result.success();
    }

    /**
     * 个人数据分页查询
     * @param personalDataPageQueryDTO
     * @return
     */
    @GetMapping
    public Result<PageResult> pageQuery(PersonalDataPageQueryDTO personalDataPageQueryDTO){
        log.info("个人数据分页查询：{}", personalDataPageQueryDTO);
        PageResult pageResult = personalDataService.pageQuery(personalDataPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改个人数据
     * @param id
     * @param personalDataUpdateDTO
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody PersonalDataUpdateDTO personalDataUpdateDTO){
        log.info("修改个人数据ID：{}，信息：{}", id, personalDataUpdateDTO);
        personalDataService.update(id, personalDataUpdateDTO);
        return Result.success();
    }

    /**
     * 根据ID获取个人数据信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<PersonalDataUpdateVO> getById(@PathVariable Long id){
        log.info("获取个人数据信息ID：{}", id);
        PersonalDataUpdateVO personalDataUpdateVO = personalDataService.getById(id);
        return Result.success(personalDataUpdateVO);
    }
}
