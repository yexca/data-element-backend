package com.yexca.service;

import com.yexca.dto.CategoryAddDTO;
import com.yexca.dto.CategoryPageQueryDTO;
import com.yexca.dto.CategoryUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.vo.CategoryListVO;
import com.yexca.vo.CategoryUpdateVO;

import java.util.List;

public interface CategoryService {
    /**
     * 增加分类
     * @param categoryAddDTO
     */
    void add(CategoryAddDTO categoryAddDTO);

    /**
     * 删除分类
     * @param id
     */
    void deleteById(Long id);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 修改分类
     * @param id
     * @param categoryUpdateDTO
     */
    void update(Long id, CategoryUpdateDTO categoryUpdateDTO);

    /**
     * 通过ID获取分类
     * @param id
     * @return
     */
    CategoryUpdateVO getById(Long id);

    /**
     * 获取全部分类
     * @return
     */
    List<CategoryListVO> list();
}
