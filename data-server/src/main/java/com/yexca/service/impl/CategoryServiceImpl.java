package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.MessageConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.CategoryAddDTO;
import com.yexca.dto.CategoryPageQueryDTO;
import com.yexca.dto.CategoryUpdateDTO;
import com.yexca.entity.Category;
import com.yexca.entity.Country;
import com.yexca.entity.EnterpriseData;
import com.yexca.entity.PersonalData;
import com.yexca.exception.CategoryException;
import com.yexca.mapper.CategoryMapper;
import com.yexca.mapper.EnterpriseDataMapper;
import com.yexca.mapper.PersonalDataMapper;
import com.yexca.result.PageResult;
import com.yexca.service.CategoryService;
import com.yexca.vo.CategoryListVO;
import com.yexca.vo.CategoryPageQueryVO;
import com.yexca.vo.CategoryUpdateVO;
import com.yexca.vo.CountryPageQueryVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PersonalDataMapper personalDataMapper;
    @Autowired
    private EnterpriseDataMapper enterpriseDataMapper;

    /**
     * 增加分类
     * @param categoryAddDTO
     */
    @Override
    public void add(CategoryAddDTO categoryAddDTO) {
        // 复制对象至实体
        Category category = new Category();
        BeanUtils.copyProperties(categoryAddDTO, category);

        // 判断是否禁用
        if(category.getStatus() == null){
            category.setStatus(StatusConstant.ENABLE);
        }

        // 创建时间与修改时间
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
        category.setCreateBy(BaseContext.getCurrentEmpId());
        category.setUpdateBy(BaseContext.getCurrentEmpId());

        categoryMapper.insert(category);
    }

    /**
     * 删除分类
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // 判断是否有该分类的数据
        // 个人数据
        List<PersonalData> personalDataList = personalDataMapper.getByCategoryId(id);
        if(personalDataList != null){
            throw new CategoryException(MessageConstant.CATEGORY_DELETE_FAILED_PERSONAL);
        }
        // 企业数据
        List<EnterpriseData> enterpriseDataList = enterpriseDataMapper.getByCategoryId(id);
        if(enterpriseDataList != null){
            throw new CategoryException(MessageConstant.CATEGORY_DELETE_FAILED_ENTERPRISE);
        }
        categoryMapper.deleteById(id);
    }

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        // 获取结果
        long total = page.getTotal();
        List<Category> records = page.getResult();

        // 处理数据，新建返回体对象列表
        List<CategoryPageQueryVO> resultRecords = new ArrayList<>();

        for (Category record : records) {
            // 创建返回对象
            CategoryPageQueryVO categoryPageQueryVO = new CategoryPageQueryVO();
            BeanUtils.copyProperties(record, categoryPageQueryVO);
            // 处理数据
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                categoryPageQueryVO.setStatus("启用");
            }else{
                categoryPageQueryVO.setStatus("禁用");
            }
            // 添加列表
            resultRecords.add(categoryPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }

    /**
     * 修改分类
     * @param id
     * @param categoryUpdateDTO
     */
    @Override
    public void update(Long id, CategoryUpdateDTO categoryUpdateDTO) {
        // 复制属性
        Category category = new Category();
        BeanUtils.copyProperties(categoryUpdateDTO, category);

        // 为实体对象设置属性
        category.setCategoryId(id);
        category.setUpdateTime(LocalDateTime.now());
        category.setUpdateBy(BaseContext.getCurrentEmpId());

        categoryMapper.update(category);
    }

    /**
     * 通过ID获取分类
     * @param id
     * @return
     */
    @Override
    public CategoryUpdateVO getById(Long id) {
        Category category = categoryMapper.getById(id);

        // 拷贝属性到VO
        CategoryUpdateVO categoryUpdateVO = new CategoryUpdateVO();
        BeanUtils.copyProperties(category, categoryUpdateVO);

        return categoryUpdateVO;
    }

    /**
     * 获取全部分类
     * @return
     */
    @Override
    public List<CategoryListVO> list() {
        List<Category> categoryList = categoryMapper.list();

        // 返回对象
        List<CategoryListVO> categoryListVOList = new ArrayList<>();
        for (Category category : categoryList) {
            // 判断status
            if(category.getStatus() != StatusConstant.ENABLE){
                continue;
            }
            CategoryListVO categoryListVO = new CategoryListVO();
            BeanUtils.copyProperties(category, categoryListVO);
            categoryListVOList.add(categoryListVO);
        }


        return categoryListVOList;
    }
}
