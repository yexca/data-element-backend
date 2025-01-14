package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.ContractConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.PersonalDataAddDTO;
import com.yexca.dto.PersonalDataPageQueryDTO;
import com.yexca.dto.PersonalDataUpdateDTO;
import com.yexca.entity.ESData;
import com.yexca.entity.PersonalData;
import com.yexca.mapper.CategoryMapper;
import com.yexca.mapper.PersonalDataMapper;
import com.yexca.mapper.PersonalUserMapper;
import com.yexca.result.PageResult;
import com.yexca.service.PersonalDataService;
import com.yexca.utils.AliOssUtil;
import com.yexca.utils.ElasticSearchUtil;
import com.yexca.utils.FiscoBcosUtil;
import com.yexca.vo.PersonalDataCommonVO;
import com.yexca.vo.PersonalDataPageQueryVO;
import com.yexca.vo.PersonalDataUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {
    @Autowired
    private PersonalDataMapper personalDataMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private PersonalUserMapper personalUserMapper;
    @Autowired
    private AliOssUtil aliOssUtil;
    @Autowired
    private ElasticSearchUtil elasticSearchUtil;
    @Autowired
    private FiscoBcosUtil fiscoBcosUtil;

    /**
     * 增加个人数据
     * @param personalDataAddDTO
     */
    @Override
    public void add(PersonalDataAddDTO personalDataAddDTO, String from) {
        // 定义实体对象
        PersonalData personalData = new PersonalData();

        // 拷贝对象属性
        BeanUtils.copyProperties(personalDataAddDTO, personalData);

        // 判断是否禁用
        if(personalData.getStatus() == null){
            personalData.setStatus(StatusConstant.ENABLE);
        }

        // 判断是否有创建来源
//        if(personalData.getCreateFrom() == null){
//            personalData.setCreateFrom(FromConstant.ADMIN);
//        }
        personalData.setCreateFrom(from);

        // 判断是否有修改来源
//        if(personalData.getUpdateFrom() == null){
//            personalData.setUpdateFrom(FromConstant.ADMIN);
//        }
        personalData.setUpdateFrom(from);

        // 创建时间与修改时间
        personalData.setCreateTime(LocalDateTime.now());
        personalData.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
//        personalData.setCreateBy(BaseContext.getCurrentEmpId());
        if(BaseContext.getCurrentEmpId() != null){
            personalData.setCreateBy(BaseContext.getCurrentEmpId());
        }else {
            personalData.setCreateBy(BaseContext.getCurrentUserId());
        }
//        personalData.setUpdateBy(BaseContext.getCurrentEmpId());
        if(BaseContext.getCurrentEmpId() != null){
            personalData.setUpdateBy(BaseContext.getCurrentEmpId());
        }else {
            personalData.setUpdateBy(BaseContext.getCurrentUserId());
        }

        personalDataMapper.insert(personalData);


        // 增加至ES
        ESData esData = new ESData();
        BeanUtils.copyProperties(personalData, esData);
        // username
        esData.setUsername(personalUserMapper.getNicknameById(personalData.getUserId()));
        // userRole
        esData.setUserRole(101);
        // categoryName
        esData.setCategoryName(categoryMapper.getNameById(personalData.getCategoryId()));
        // 数据ID
        String id = "personal_" + personalData.getDataId();
        elasticSearchUtil.insert(id, esData);

        // 增加至区块链，构建数据
        String dataId = "personal_data_" + personalData.getDataId();
        String userId = "personal_user_" + personalData.getUserId();
        List<Object> params = new ArrayList<>();
        params.add(dataId);
        params.add(userId);
        // 发送请求
        fiscoBcosUtil.add(params, ContractConstant.PERSONAL_DATA, ContractConstant.PERSONAL_DATA_ADDRESS);
//        List<Object> objectList = fiscoBcosUtil.add(params, ContractConstant.PERSONAL_DATA, ContractConstant.PERSONAL_DATA_ADDRESS);
//        for (Object object : objectList) {
//            System.out.println("区块链返回：" + object);
//        }
    }

    /**
     * 根据ID删除数据
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // OSS删除
        aliOssUtil.delete(personalDataMapper.getById(id).getFileLink());
        // 数据库删除
        personalDataMapper.deleteById(id);
        // ES删除
        String esId = "personal_" + id.toString();
        elasticSearchUtil.delete(esId);
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
            personalDataPageQueryVO.setUsername(personalUserMapper.getUsernameById(record.getUserId()));
            // 分类
            personalDataPageQueryVO.setCategoryName(categoryMapper.getNameById(record.getCategoryId()));
            // 处理状态信息
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                personalDataPageQueryVO.setStatus("启用");
            }else{
                personalDataPageQueryVO.setStatus("禁用");
            }

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
    public void update(Long id, PersonalDataUpdateDTO personalDataUpdateDTO, String from) {
        // 实体对象用于Mapper
        PersonalData personalData = new PersonalData();
        BeanUtils.copyProperties(personalDataUpdateDTO, personalData);
        personalData.setDataId(id);

        // 设置修改时间
        personalData.setUpdateTime(LocalDateTime.now());
        // 修改人
        if(BaseContext.getCurrentEmpId() != null){
            personalData.setUpdateBy(BaseContext.getCurrentEmpId());
        }else {
            personalData.setUpdateBy(BaseContext.getCurrentUserId());
        }

        // 修改端
        personalData.setUpdateFrom(from);

        personalDataMapper.update(personalData);

        // ES
        ESData esData = new ESData();
        BeanUtils.copyProperties(personalData, esData);
        // username
        esData.setUsername(personalUserMapper.getNicknameById(personalData.getUserId()));
        // userRole
        esData.setUserRole(101);
        // categoryName
        esData.setCategoryName(categoryMapper.getNameById(personalData.getCategoryId()));
        // 数据ID
        String esId = "personal_" + id;
        elasticSearchUtil.update(esId, esData);
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

    /**
     * 根据Id获取个人数据Common信息
     * @param dataId
     * @return
     */
    @Override
    public PersonalDataCommonVO getCommonById(Long dataId) {
        PersonalData personalData = personalDataMapper.getById(dataId);
        PersonalDataCommonVO personalDataCommonVO = new PersonalDataCommonVO();
        BeanUtils.copyProperties(personalData, personalDataCommonVO);
        // 用户
        personalDataCommonVO.setUsername(personalUserMapper.getUsernameById(personalData.getUserId()));
        // 分类
        personalDataCommonVO.setCategoryName(categoryMapper.getNameById(personalData.getCategoryId()));
        // 状态
        Integer status = personalData.getStatus();
        if(status.equals(StatusConstant.ENABLE)){
            personalDataCommonVO.setStatus("启用");
        }else{
            personalDataCommonVO.setStatus("禁用");
        }
        return personalDataCommonVO;
    }

    /**
     * 用户查询用户数据
     * @param currentUserId
     * @param personalDataPageQueryDTO
     * @return
     */
    @Override
    public PageResult userPageQuery(Long currentUserId, PersonalDataPageQueryDTO personalDataPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(personalDataPageQueryDTO.getPage(), personalDataPageQueryDTO.getPageSize());
        // Mapper层-查询自己的数据
        Page<PersonalData> page;
        if(Objects.equals(currentUserId, personalDataPageQueryDTO.getUserId())){
            page = personalDataMapper.pageQuery(personalDataPageQueryDTO);
        }else {
            personalDataPageQueryDTO.setStatus(StatusConstant.ENABLE);
            page = personalDataMapper.pageQuery(personalDataPageQueryDTO);
        }

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
            personalDataPageQueryVO.setUsername(personalUserMapper.getUsernameById(record.getUserId()));
            // 分类
            personalDataPageQueryVO.setCategoryName(categoryMapper.getNameById(record.getCategoryId()));
            // 处理状态信息
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                personalDataPageQueryVO.setStatus("启用");
            }else{
                personalDataPageQueryVO.setStatus("禁用");
            }

            resultRecords.add(personalDataPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }
}
