package com.yexca.controller.user;

import com.yexca.result.Result;
import com.yexca.service.CategoryService;
import com.yexca.vo.CategoryListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCategoryController")
@RequestMapping("/user/category")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取全部分类
     * @return
     */
    @GetMapping("/list")
    public Result<List<CategoryListVO>> list(){
        log.info("获取全部分类");
        List<CategoryListVO> categoryListVOList = categoryService.list();
        return Result.success(categoryListVOList);
    }
}
