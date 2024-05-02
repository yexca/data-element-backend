package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.MessageConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.CountryAddDTO;
import com.yexca.dto.CountryPageQueryDTO;
import com.yexca.dto.CountryUpdateDTO;
import com.yexca.entity.Country;
import com.yexca.entity.Employee;
import com.yexca.entity.EnterpriseUser;
import com.yexca.entity.PersonalUser;
import com.yexca.exception.CountryException;
import com.yexca.mapper.CountryMapper;
import com.yexca.mapper.EmployeeMapper;
import com.yexca.mapper.EnterpriseUserMapper;
import com.yexca.mapper.PersonalUserMapper;
import com.yexca.result.PageResult;
import com.yexca.service.CountryService;
import com.yexca.vo.CountryListVO;
import com.yexca.vo.CountryPageQueryVO;
import com.yexca.vo.CountryUpdateVO;
import com.yexca.vo.EmployeePageQueryVO;
import org.apache.poi.ss.formula.functions.Count;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private PersonalUserMapper personalUserMapper;
    @Autowired
    private EnterpriseUserMapper enterpriseUserMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取全部国家
     * @return
     */
    @Override
    public List<CountryListVO> list() {
        return countryMapper.list();
    }

    /**
     * 新增国家
     * @param countryAddDTO
     */
    @Override
    public void add(CountryAddDTO countryAddDTO) {
        // 复制属性
        Country country = new Country();
        BeanUtils.copyProperties(countryAddDTO, country);

        // 判断是否禁用
        if(country.getStatus() == null){
            country.setStatus(StatusConstant.ENABLE);
        }

        // 创建时间与修改时间
        country.setCreateTime(LocalDateTime.now());
        country.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
        country.setCreateBy(BaseContext.getCurrentEmpId());
        country.setUpdateBy(BaseContext.getCurrentEmpId());

        countryMapper.insert(country);
    }

    /**
     * 删除国家
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        // 判断是否有该国家的人
        // 个人用户
        List<PersonalUser> personalUserList = personalUserMapper.getByCountryId(id);
        if(personalUserList != null){
            throw new CountryException(MessageConstant.COUNTRY_DELETE_FAILED_PERSONAL);
        }
        // 企业用户
        List<EnterpriseUser> enterpriseUserList = enterpriseUserMapper.getByCountryId(id);
        if(enterpriseUserList != null){
            throw new CountryException(MessageConstant.COUNTRY_DELETE_FAILED_ENTERPRISE);
        }
        // 员工
        List<Employee> employeeList = employeeMapper.getByCountryId(id);
        if(employeeList != null){
            throw new CountryException(MessageConstant.COUNTRY_DELETE_FAILED_EMPLOYEE);
        }

        countryMapper.deleteById(id);
    }

    /**
     * 分页查询
     * @param countryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CountryPageQueryDTO countryPageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(countryPageQueryDTO.getPage(), countryPageQueryDTO.getPageSize());
        Page<Country> page = countryMapper.pageQuery(countryPageQueryDTO);
        // 获取结果
        long total = page.getTotal();
        List<Country> records = page.getResult();

        // 处理数据，新建返回体对象列表
        List<CountryPageQueryVO> resultRecords = new ArrayList<>();

        for (Country record : records) {
            // 创建返回对象
            CountryPageQueryVO countryPageQueryVO = new CountryPageQueryVO();
            BeanUtils.copyProperties(record, countryPageQueryVO);
            // 处理数据
            Integer status = record.getStatus();
            if(status.equals(StatusConstant.ENABLE)){
                countryPageQueryVO.setStatus("启用");
            }else{
                countryPageQueryVO.setStatus("禁用");
            }
            // 添加列表
            resultRecords.add(countryPageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }

    /**
     * 修改国家信息
     * @param id
     * @param countryUpdateDTO
     */
    @Override
    public void update(Long id, CountryUpdateDTO countryUpdateDTO) {
        // 复制属性给实体对象
        Country country = new Country();
        BeanUtils.copyProperties(countryUpdateDTO, country);
        // 为实体对象设置属性
        country.setCountryId(id);
        country.setUpdateTime(LocalDateTime.now());
        country.setUpdateBy(BaseContext.getCurrentEmpId());

        countryMapper.update(country);
    }

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    @Override
    public CountryUpdateVO getById(Long id) {
        Country country = countryMapper.getById(id);

        // 复制给VO
        CountryUpdateVO countryUpdateVO = new CountryUpdateVO();
        BeanUtils.copyProperties(country, countryUpdateVO);

        return countryUpdateVO;
    }
}
