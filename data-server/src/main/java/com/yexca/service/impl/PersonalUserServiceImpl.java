package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.FromConstant;
import com.yexca.constant.GenderConstant;
import com.yexca.constant.PasswordConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.PersonalUserAddDTO;
import com.yexca.dto.PersonalUserPageQueryDTO;
import com.yexca.dto.PersonalUserUpdateDTO;
import com.yexca.entity.Employee;
import com.yexca.entity.PersonalUser;
import com.yexca.mapper.CountryMapper;
import com.yexca.mapper.PersonalUserMapper;
import com.yexca.result.PageResult;
import com.yexca.service.PersonalUserService;
import com.yexca.vo.EmployeePageQueryVO;
import com.yexca.vo.PersonalUserPageQueryVO;
import com.yexca.vo.PersonalUserUpdateVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonalUserServiceImpl implements PersonalUserService {
    @Autowired
    private PersonalUserMapper personalUserMapper;
    @Autowired
    private CountryMapper countryMapper;
    /**
     * 增加个人用户
     * @param personalUserAddDTO
     */
    @Override
    public void add(PersonalUserAddDTO personalUserAddDTO) {
        PersonalUser personalUser = new PersonalUser();

        // 拷贝对象属性
        BeanUtils.copyProperties(personalUserAddDTO, personalUser);

        // 判断密码是否存在
        if(personalUser.getPassword() == null){
            personalUser.setPassword(DigestUtils.sha1Hex(PasswordConstant.DEFAULT_PASSWORD));
        }else {
            personalUser.setPassword(DigestUtils.sha1Hex(personalUser.getPassword()));
        }

        // 手机号
        if(personalUserAddDTO.getPhone() != null){
            personalUser.setPhone(personalUserAddDTO.getPhone().toString());
        }

        // 判断昵称是否存在
        if(personalUser.getNickname() == null){
            personalUser.setNickname(personalUser.getUsername());
        }

        // 判断是否禁用
        if(personalUser.getStatus() == null){
            personalUser.setStatus(StatusConstant.ENABLE);
        }

        // 判断是否有创建来源
        if(personalUser.getCreateFrom() == null){
            personalUser.setCreateFrom(FromConstant.ADMIN);
        }

        // 判断是否有修改来源
        if(personalUser.getUpdateFrom() == null){
            personalUser.setUpdateFrom(FromConstant.ADMIN);
        }

        // 创建时间与修改时间
        personalUser.setCreateTime(LocalDateTime.now());
        personalUser.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
        personalUser.setCreateBy(BaseContext.getCurrentEmpId());
        personalUser.setUpdateBy(BaseContext.getCurrentEmpId());

        // Mapper插入数据
        personalUserMapper.insert(personalUser);
    }

    /**
     * 通过id删除用户
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        personalUserMapper.deleteById(id);
    }

    /**
     * 个人用户分页查询
     * @param personalUserPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(PersonalUserPageQueryDTO personalUserPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(personalUserPageQueryDTO.getPage(), personalUserPageQueryDTO.getPageSize());
        Page<PersonalUser> page = personalUserMapper.pageQuery(personalUserPageQueryDTO);
        // 获取总条数
        long total = page.getTotal();
        // 获取记录
        List<PersonalUser> records = page.getResult();

        // 处理数据
            // 新建返回对象列表
        List<PersonalUserPageQueryVO> resultRecords = new ArrayList<>();
        // 循环处理每一条数据
        for (PersonalUser record : records) {
            // 处理信息
            PersonalUserPageQueryVO personalUserPageQueryVO = handleEmployeeToVO(record);
            // 添加至列表
            resultRecords.add(personalUserPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }

    /**
     * 修改个人用户
     * @param id
     * @param personalUserUpdateDTO
     */
    @Override
    public void update(Long id, PersonalUserUpdateDTO personalUserUpdateDTO) {
        PersonalUser personalUser = new PersonalUser();
        // 拷贝属性
        BeanUtils.copyProperties(personalUserUpdateDTO, personalUser);
        // 设置ID
        personalUser.setUserId(id);

        // 加密密码
        if(personalUser.getPassword() != null){
            personalUser.setPassword(DigestUtils.sha1Hex(personalUser.getPassword()));
        }

        // 手机号转换
        if(personalUserUpdateDTO.getPhone() != null){
            personalUser.setPhone(personalUserUpdateDTO.getPhone().toString());
        }

        // 设置修改时间
        personalUser.setUpdateTime(LocalDateTime.now());
        // 修改人
        personalUser.setUpdateBy(BaseContext.getCurrentEmpId());
        // 修改端
        personalUser.setUpdateFrom(FromConstant.ADMIN);

        personalUserMapper.update(personalUser);
    }

    /**
     * 通过ID获取用户信息
     * @param id
     * @return
     */
    @Override
    public PersonalUserUpdateVO getById(Long id) {
        PersonalUser personalUser = personalUserMapper.getById(id);

        // 拷贝属性到返回对象
        PersonalUserUpdateVO personalUserUpdateVO = new PersonalUserUpdateVO();
        BeanUtils.copyProperties(personalUser, personalUserUpdateVO);

        // 转换手机号
        if(personalUser.getPhone() != null){
            personalUserUpdateVO.setPhone(Long.parseLong(personalUser.getPhone()));
        }

        return personalUserUpdateVO;
    }

    /**
     * 将PersonalUser的信息补全
     * @param record
     * @return
     */
    private PersonalUserPageQueryVO handleEmployeeToVO(PersonalUser record) {
        // 返回对象
        PersonalUserPageQueryVO personalUserPageQueryVO = new PersonalUserPageQueryVO();
        // 复制属性
        BeanUtils.copyProperties(record, personalUserPageQueryVO);

        // 处理国家信息
        String countryName = countryMapper.getNameByCountryId(record.getCountryId());
        personalUserPageQueryVO.setCountryName(countryName);

        // 处理手机号
        if (personalUserPageQueryVO.getPhone() != null){
            Integer phone = countryMapper.getPhoneById(record.getCountryId());
            personalUserPageQueryVO.setPhone("+" + phone + " " + personalUserPageQueryVO.getPhone());
        }

        // 处理性别信息
        Integer gender = record.getGender();
        if(gender.equals(GenderConstant.UNKNOWN)){
            personalUserPageQueryVO.setGender("未知");
        }else if(gender.equals(GenderConstant.MALE)){
            personalUserPageQueryVO.setGender("男");
        }else{
            personalUserPageQueryVO.setGender("女");
        }

        // 处理状态信息
        Integer status = record.getStatus();
        if(status.equals(StatusConstant.ENABLE)){
            personalUserPageQueryVO.setStatus("启用");
        }else{
            personalUserPageQueryVO.setStatus("禁用");
        }

        return personalUserPageQueryVO;
    }
}
