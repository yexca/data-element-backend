package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.FromConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.PersonalDataAddDTO;
import com.yexca.dto.PersonalDataPageQueryDTO;
import com.yexca.dto.PersonalDataUpdateDTO;
import com.yexca.entity.PersonalData;
import com.yexca.mapper.CategoryMapper;
import com.yexca.mapper.PersonalDataMapper;
import com.yexca.mapper.PersonalUserMapper;
import com.yexca.result.PageResult;
import com.yexca.service.PersonalDataService;
import com.yexca.vo.PersonalDataPageQueryVO;
import com.yexca.vo.PersonalDataUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {
    @Autowired
    private PersonalDataMapper personalDataMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PersonalUserMapper personalUserMapper;

    /**
     * 增加个人数据
     * @param personalDataAddDTO
     */
    @Override
    public void add(PersonalDataAddDTO personalDataAddDTO) {
        // 定义实体对象
        PersonalData personalData = new PersonalData();

        // 拷贝对象属性
        BeanUtils.copyProperties(personalDataAddDTO, personalData);

        // 判断是否禁用
        if(personalData.getStatus() == null){
            personalData.setStatus(StatusConstant.ENABLE);
        }

        // 判断是否有创建来源
        if(personalData.getCreateFrom() == null){
            personalData.setCreateFrom(FromConstant.ADMIN);
        }

        // 判断是否有修改来源
        if(personalData.getUpdateFrom() == null){
            personalData.setUpdateFrom(FromConstant.ADMIN);
        }

        // 创建时间与修改时间
        personalData.setCreateTime(LocalDateTime.now());
        personalData.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
        personalData.setCreateBy(BaseContext.getCurrentEmpId());
        personalData.setUpdateBy(BaseContext.getCurrentEmpId());

        personalDataMapper.insert(personalData);
    }

    /**
     * 根据ID删除数据
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        personalDataMapper.deleteById(id);
    }

    /**
     * 个人数据分页查询
     * @param personalDataPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(PersonalDataPageQueryDTO personalDataPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(personalDataPageQueryDTO.getPage(), personalDataPageQueryDTO.getPageSize());
        // Mapper层
        Page<PersonalData> page = personalDataMapper.pageQuery(personalDataPageQueryDTO);

        // 查询结束，获取结果
        long total = page.getTotal();
        List<PersonalData> records = page.getResult();

        // 处理数据--新建返回对象
        List<PersonalDataPageQueryVO> resultRecords = new ArrayList<>();

        // 循环处理数据
        for (PersonalData record : records) {
            // 新建返回对象
            PersonalDataPageQueryVO personalDataPageQueryVO = new PersonalDataPageQueryVO();
            BeanUtils.copyProperties(record, personalDataPageQueryVO);
            // 用户名
            personalDataPageQueryVO.setUserName(personalUserMapper.getUsernameById(record.getUserId()));
            // 分类
            personalDataPageQueryVO.setCategoryName(categoryMapper.getNameById(record.getCategoryId()));

            resultRecords.add(personalDataPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }

    /**
     * 修改个人数据
     * @param id
     * @param personalDataUpdateDTO
     */
    @Override
    public void update(Long id, PersonalDataUpdateDTO personalDataUpdateDTO) {
        // 实体对象用于Mapper
        PersonalData personalData = new PersonalData();
        BeanUtils.copyProperties(personalDataUpdateDTO, personalData);
        personalData.setDataId(id);

        // 设置修改时间
        personalData.setUpdateTime(LocalDateTime.now());
        // 修改人
        personalData.setUpdateBy(BaseContext.getCurrentEmpId());
        // 修改端
        personalData.setUpdateFrom(FromConstant.ADMIN);

        personalDataMapper.update(personalData);
    }

    /**
     * 根据ID获取个人数据信息
     * @param id
     * @return
     */
    @Override
    public PersonalDataUpdateVO getById(Long id) {
        PersonalData personalData = personalDataMapper.getById(id);
        // 复制属性到返回对象
        PersonalDataUpdateVO personalDataUpdateVO = new PersonalDataUpdateVO();
        BeanUtils.copyProperties(personalData, personalDataUpdateVO);
        return personalDataUpdateVO;
    }
}
