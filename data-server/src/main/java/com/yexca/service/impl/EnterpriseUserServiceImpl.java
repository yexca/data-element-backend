package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.FromConstant;
import com.yexca.constant.GenderConstant;
import com.yexca.constant.PasswordConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.EnterpriseUserAddDTO;
import com.yexca.dto.EnterpriseUserPageQueryDTO;
import com.yexca.dto.EnterpriseUserUpdateDTO;
import com.yexca.entity.Employee;
import com.yexca.entity.EnterpriseUser;
import com.yexca.mapper.CountryMapper;
import com.yexca.mapper.EnterpriseUserMapper;
import com.yexca.result.PageResult;
import com.yexca.service.EnterpriseUserService;
import com.yexca.vo.EmployeePageQueryVO;
import com.yexca.vo.EnterpriseUserPageQueryVO;
import com.yexca.vo.EnterpriseUserUpdateVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnterpriseUserServiceImpl implements EnterpriseUserService {
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private CountryMapper countryMapper;
    /**
     * 添加企业用户
     * @param enterpriseUserAddDTO
     */
    @Override
    public void add(EnterpriseUserAddDTO enterpriseUserAddDTO) {
        // 创建企业用户对象
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        // 拷贝对象
        BeanUtils.copyProperties(enterpriseUserAddDTO, enterpriseUser);

        // 判断密码是否存在
        if(enterpriseUser.getPassword() == null){
            enterpriseUser.setPassword(DigestUtils.sha1Hex(PasswordConstant.DEFAULT_PASSWORD));
        }else {
            enterpriseUser.setPassword(DigestUtils.sha1Hex(enterpriseUser.getPassword()));
        }

        // 判断是否禁用
        if(enterpriseUser.getStatus() == null){
        enterpriseUser.setStatus(StatusConstant.ENABLE);
        }

        // 判断是否有创建来源
        if(enterpriseUser.getCreateFrom() == null){
            enterpriseUser.setCreateFrom(FromConstant.ADMIN);
        }

        // 判断是否有修改来源
        if(enterpriseUser.getUpdateFrom() == null){
            enterpriseUser.setUpdateFrom(FromConstant.ADMIN);
        }

        // 创建时间与修改时间
        enterpriseUser.setCreateTime(LocalDateTime.now());
        enterpriseUser.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
        enterpriseUser.setCreateBy(BaseContext.getCurrentEmpId());
        enterpriseUser.setUpdateBy(BaseContext.getCurrentEmpId());

        // Mapper插入数据
        enterpriseUserMapper.insert(enterpriseUser);
    }

    /**
     * 删除企业用户
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        enterpriseUserMapper.deleteById(id);
    }

    /**
     * 企业用户分页查询
     * @param enterpriseUserPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(EnterpriseUserPageQueryDTO enterpriseUserPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(enterpriseUserPageQueryDTO.getPage(), enterpriseUserPageQueryDTO.getPageSize());
        // Mapper层
        Page<EnterpriseUser> page = enterpriseUserMapper.pageQuery(enterpriseUserPageQueryDTO);

        // 查询结束，获取结果
        long total = page.getTotal();
        List<EnterpriseUser> records = page.getResult();

        // 处理数据，新建返回体对象列表
        List<EnterpriseUserPageQueryVO> resultRecords = new ArrayList<>();

        // 循环处理每一条数据
        for (EnterpriseUser record : records) {
            // 处理信息，返回对象
            EnterpriseUserPageQueryVO enterpriseUserPageQueryVO = new EnterpriseUserPageQueryVO();
            // 复制属性
            BeanUtils.copyProperties(record, enterpriseUserPageQueryVO);
            // 处理国家信息
            Integer countryId = record.getCountryId();
            String countryName = countryMapper.getNameByCountryId(countryId);
            enterpriseUserPageQueryVO.setCountryName(countryName);
            // 处理状态信息
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                enterpriseUserPageQueryVO.setStatus("启用");
            }else{
                enterpriseUserPageQueryVO.setStatus("禁用");
            }
            // 添加至列表
            resultRecords.add(enterpriseUserPageQueryVO);
        }

        // 返回结果
        return new PageResult(total, resultRecords);
    }

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    @Override
    public EnterpriseUserUpdateVO getById(Long id) {
        EnterpriseUser enterpriseUser = enterpriseUserMapper.getById(id);
        // 复制属性至返回对象
        EnterpriseUserUpdateVO enterpriseUserUpdateVO = new EnterpriseUserUpdateVO();
        BeanUtils.copyProperties(enterpriseUser, enterpriseUserUpdateVO);
        return enterpriseUserUpdateVO;
    }

    /**
     * 修改企业用户
     * @param id
     * @param enterpriseUserUpdateDTO
     */
    @Override
    public void update(Long id, EnterpriseUserUpdateDTO enterpriseUserUpdateDTO) {
        // 新建实体
        EnterpriseUser enterpriseUser = new EnterpriseUser();
        // 复制属性
        BeanUtils.copyProperties(enterpriseUserUpdateDTO, enterpriseUser);
        enterpriseUser.setUserId(id);

        // 密码加密
        if (enterpriseUser.getPassword() != null){
            enterpriseUser.setPassword(DigestUtils.sha1Hex(enterpriseUser.getPassword()));
        }

        // 修改人
        enterpriseUser.setUpdateBy(BaseContext.getCurrentEmpId());

        // 修改时间
        enterpriseUser.setUpdateTime(LocalDateTime.now());

        // 修改自
        if (enterpriseUser.getUpdateFrom() == null){
            enterpriseUser.setUpdateFrom(FromConstant.ADMIN);
        }

        enterpriseUserMapper.update(enterpriseUser);
    }

}
