package com.yexca.controller.admin;

import com.yexca.dto.CategoryAddDTO;
import com.yexca.dto.CategoryPageQueryDTO;
import com.yexca.dto.CategoryUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.CategoryService;
import com.yexca.vo.CategoryUpdateVO;
import com.yexca.vo.CountryListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 增加分类
     * @param categoryAddDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody CategoryAddDTO categoryAddDTO){
        log.info("增加分类：{}", categoryAddDTO);
        categoryService.add(categoryAddDTO);
        return Result.success();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        log.info("删除分类ID：{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping
    public Result<PageResult> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO){
        log.info("分类分页查询：{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 根据ID修改分类
     * @param id
     * @param categoryUpdateDTO
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody CategoryUpdateDTO categoryUpdateDTO){
        log.info("修改分类ID：{}，信息：{}", id, categoryUpdateDTO);
        categoryService.update(id, categoryUpdateDTO);
        return Result.success();
    }

    /**
     * 通过Id获取分类
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<CategoryUpdateVO> getById(@PathVariable Long id){
        log.info("获取分类ID：{}", id);
        CategoryUpdateVO categoryUpdateVO = categoryService.getById(id);
        return Result.success(categoryUpdateVO);
    }
}
