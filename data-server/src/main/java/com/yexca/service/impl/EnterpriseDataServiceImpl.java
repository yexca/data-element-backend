package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.FromConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.EnterpriseDataAddDTO;
import com.yexca.dto.EnterpriseDataPageQueryDTO;
import com.yexca.dto.EnterpriseDataUpdateDTO;
import com.yexca.entity.ESData;
import com.yexca.entity.EnterpriseData;
import com.yexca.entity.PersonalData;
import com.yexca.mapper.CategoryMapper;
import com.yexca.mapper.EnterpriseDataMapper;
import com.yexca.mapper.EnterpriseUserMapper;
import com.yexca.result.PageResult;
import com.yexca.service.EnterpriseDataService;
import com.yexca.utils.AliOssUtil;
import com.yexca.utils.ElasticSearchUtil;
import com.yexca.vo.EnterpriseDataPageQueryVO;
import com.yexca.vo.EnterpriseDataUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EnterpriseDataServiceImpl implements EnterpriseDataService {
    @Autowired
    private EnterpriseDataMapper enterpriseDataMapper;
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AliOssUtil aliOssUtil;
    @Autowired
    private ElasticSearchUtil elasticSearchUtil;

    /**
     * 新增企业用户数据
     * @param enterpriseDataAddDTO
     */
    @Override
    public void add(EnterpriseDataAddDTO enterpriseDataAddDTO, String from) {
        // 新建实体对象
        EnterpriseData enterpriseData = new EnterpriseData();
        BeanUtils.copyProperties(enterpriseDataAddDTO, enterpriseData);

        // 判断是否禁用
        if(enterpriseData.getStatus() == null){
            enterpriseData.setStatus(StatusConstant.ENABLE);
        }

        // 判断是否有创建来源
//        if(enterpriseData.getCreateFrom() == null){
//            enterpriseData.setCreateFrom(FromConstant.ADMIN);
//        }
        enterpriseData.setUpdateFrom(from);

        // 判断是否有修改来源
//        if(enterpriseData.getUpdateFrom() == null){
//            enterpriseData.setUpdateFrom(FromConstant.ADMIN);
//        }
        enterpriseData.setUpdateFrom(from);

        // 创建时间与修改时间
        enterpriseData.setCreateTime(LocalDateTime.now());
        enterpriseData.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
//        enterpriseData.setCreateBy(BaseContext.getCurrentEmpId());
        if(BaseContext.getCurrentEmpId() != null){
            enterpriseData.setCreateBy(BaseContext.getCurrentEmpId());
        }else {
            enterpriseData.setCreateBy(BaseContext.getCurrentUserId());
        }
//        enterpriseData.setUpdateBy(BaseContext.getCurrentEmpId());
        if(BaseContext.getCurrentEmpId() != null){
            enterpriseData.setUpdateBy(BaseContext.getCurrentEmpId());
        }else {
            enterpriseData.setUpdateBy(BaseContext.getCurrentUserId());
        }
        enterpriseDataMapper.insert(enterpriseData);

        // 上传至ES
        ESData esData = new ESData();
        BeanUtils.copyProperties(enterpriseData, esData);
        // username - enterpriseName
        esData.setUsername(enterpriseUserMapper.getEnterpriseNameByID(enterpriseData.getUserId()));
        // userRole
        esData.setUserRole(102);
        // categoryName
        esData.setCategoryName(categoryMapper.getNameById(enterpriseData.getCategoryId()));
        // 数据ID
        String id = "enterprise_" + enterpriseData.getDataId();
        elasticSearchUtil.insert(id, esData);
    }

    /**
     * 通过ID删除企业数据
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // OSS删除
        aliOssUtil.delete(enterpriseDataMapper.getById(id).getSampleFileLink());
        // 数据库删除
        enterpriseDataMapper.deleteById(id);
        // ES删除
        String esId = "enterprise_" + id.toString();
        elasticSearchUtil.delete(esId);
    }

    /**
     * 企业数据分页查询
     * @param enterpriseDataPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(enterpriseDataPageQueryDTO.getPage(), enterpriseDataPageQueryDTO.getPageSize());
        Page<EnterpriseData> page = enterpriseDataMapper.pageQuery(enterpriseDataPageQueryDTO);

        // 查询结束，获取结果
        long total = page.getTotal();
        List<EnterpriseData> records = page.getResult();

        // 返回数据列表
        List<EnterpriseDataPageQueryVO> resultRecords = new ArrayList<>();
        //处理数据
        for (EnterpriseData record : records) {
            // 新建返回对象
            EnterpriseDataPageQueryVO enterpriseDataPageQueryVO = new EnterpriseDataPageQueryVO();

            //处理
            BeanUtils.copyProperties(record, enterpriseDataPageQueryVO);
            // 用户名
            enterpriseDataPageQueryVO.setUsername(enterpriseUserMapper.getUsernameById(record.getUserId()));
            // 分类
            enterpriseDataPageQueryVO.setCategoryName(categoryMapper.getNameById(record.getCategoryId()));
            // 处理状态信息
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                enterpriseDataPageQueryVO.setStatus("启用");
            }else{
                enterpriseDataPageQueryVO.setStatus("禁用");
            }

            // 添加到返回列表
            resultRecords.add(enterpriseDataPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }

    /**
     * 更新企业数据
     * @param id
     * @param enterpriseDataUpdateDTO
     */
    @Override
    public void update(Long id, EnterpriseDataUpdateDTO enterpriseDataUpdateDTO, String from) {
        // 复制属性至实体对象
        EnterpriseData enterpriseData = new EnterpriseData();
        BeanUtils.copyProperties(enterpriseDataUpdateDTO, enterpriseData);

        // 处理属性
        enterpriseData.setDataId(id);

        // 设置修改时间
        enterpriseData.setUpdateTime(LocalDateTime.now());
        // 修改人
//        enterpriseData.setUpdateBy(BaseContext.getCurrentEmpId());
        if(BaseContext.getCurrentEmpId() != null){
            enterpriseData.setUpdateBy(BaseContext.getCurrentEmpId());
        }else {
            enterpriseData.setUpdateBy(BaseContext.getCurrentUserId());
        }
        // 修改端
        enterpriseData.setUpdateFrom(from);

        enterpriseDataMapper.update(enterpriseData);

        // 上传至ES
        ESData esData = new ESData();
        BeanUtils.copyProperties(enterpriseData, esData);
        // username - enterpriseName
        esData.setUsername(enterpriseUserMapper.getEnterpriseNameByID(enterpriseData.getUserId()));
        // userRole
        esData.setUserRole(102);
        // categoryName
        esData.setCategoryName(categoryMapper.getNameById(enterpriseData.getCategoryId()));
        // 数据ID
        String esId = "enterprise_" + id;
        elasticSearchUtil.insert(esId, esData);
    }

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    @Override
    public EnterpriseDataUpdateVO getById(Long id) {
        EnterpriseData enterpriseData = enterpriseDataMapper.getById(id);
        // 复制属性到返回对象
        EnterpriseDataUpdateVO enterpriseDataUpdateVO = new EnterpriseDataUpdateVO();
        BeanUtils.copyProperties(enterpriseData, enterpriseDataUpdateVO);
        // 获取用户名
        enterpriseDataUpdateVO.setUsername(enterpriseUserMapper.getUsernameById(enterpriseData.getUserId()));
        return enterpriseDataUpdateVO;
    }

    /**
     * 用户端分页查询
     * @param currentUserId
     * @param enterpriseDataPageQueryDTO
     * @return
     */
    @Override
    public PageResult userPageQuery(Long currentUserId, EnterpriseDataPageQueryDTO enterpriseDataPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(enterpriseDataPageQueryDTO.getPage(), enterpriseDataPageQueryDTO.getPageSize());
        // Mapper层-查询自己的数据
        Page<EnterpriseData> page;
        if(Objects.equals(currentUserId, enterpriseDataPageQueryDTO.getUserId())){
            page = enterpriseDataMapper.pageQuery(enterpriseDataPageQueryDTO);
        }else {
            enterpriseDataPageQueryDTO.setStatus(StatusConstant.ENABLE);
            page = enterpriseDataMapper.pageQuery(enterpriseDataPageQueryDTO);
        }

        // 查询结束，获取结果
        long total = page.getTotal();
        List<EnterpriseData> records = page.getResult();

        // 返回数据列表
        List<EnterpriseDataPageQueryVO> resultRecords = new ArrayList<>();
        //处理数据
        for (EnterpriseData record : records) {
            // 新建返回对象
            EnterpriseDataPageQueryVO enterpriseDataPageQueryVO = new EnterpriseDataPageQueryVO();

            //处理
            BeanUtils.copyProperties(record, enterpriseDataPageQueryVO);
            // 用户名
            enterpriseDataPageQueryVO.setUsername(enterpriseUserMapper.getUsernameById(record.getUserId()));
            // 分类
            enterpriseDataPageQueryVO.setCategoryName(categoryMapper.getNameById(record.getCategoryId()));
            // 处理状态信息
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                enterpriseDataPageQueryVO.setStatus("启用");
            }else{
                enterpriseDataPageQueryVO.setStatus("禁用");
            }

            // 添加到返回列表
            resultRecords.add(enterpriseDataPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }
}
