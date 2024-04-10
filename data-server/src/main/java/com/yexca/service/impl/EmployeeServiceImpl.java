package com.yexca.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yexca.constant.GenderConstant;
import com.yexca.constant.MessageConstant;
import com.yexca.constant.PasswordConstant;
import com.yexca.constant.StatusConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.EmployeeAddDTO;
import com.yexca.dto.EmployeeLoginDTO;
import com.yexca.dto.EmployeePageQueryDTO;
import com.yexca.dto.EmployeeUpdateDTO;
import com.yexca.entity.Employee;
import com.yexca.exception.AccountLockedException;
import com.yexca.exception.AccountNotFoundException;
import com.yexca.exception.PasswordErrorException;
import com.yexca.mapper.CountryMapper;
import com.yexca.mapper.EmployeeMapper;
import com.yexca.mapper.RoleMapper;
import com.yexca.result.PageResult;
import com.yexca.service.EmployeeService;
import com.yexca.vo.EmployeePageQueryVO;
import com.yexca.vo.EmployeeUpdateVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // 根据用户名查询数据库的数据
        Employee employee = employeeMapper.getByUsername(username);

        // 用户不存在
        if (employee == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码比对
        if (!DigestUtils.sha1Hex(password).equals(employee.getPassword())) {
            // 密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        // 账号未启用
        if (employee.getStatus() == StatusConstant.DISABLE) {
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }

    /**
     * 增加员工
     * @param employeeAddDTO
     */
    @Override
    public void add(EmployeeAddDTO employeeAddDTO) {
        Employee employee = new Employee();

        // 拷贝对象属性
        BeanUtils.copyProperties(employeeAddDTO, employee);

        // 判断密码是否存在
        if(employee.getPassword() == null){
            employee.setPassword(DigestUtils.sha1Hex(PasswordConstant.DEFAULT_PASSWORD));
        }else {
            employee.setPassword(DigestUtils.sha1Hex(employee.getPassword()));
        }
        // 判断昵称是否存在
        if(employee.getNickname() == null){
            employee.setNickname(employee.getUsername());
        }
        // 判断是否禁用
        if(employee.getStatus() == null){
            employee.setStatus(StatusConstant.ENABLE);
        }

        // 创建时间与修改时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        // 创建人与修改人
        employee.setCreateBy(BaseContext.getCurrentEmpId());
        employee.setUpdateBy(BaseContext.getCurrentEmpId());

        // Mapper插入数据
        employeeMapper.insert(employee);
    }

    @Override
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        // 开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> records = page.getResult();

        // 处理数据，新建返回体对象列表
        List<EmployeePageQueryVO> resultRecords = new ArrayList<>();

        for (Employee record : records) {
            // 处理信息
            EmployeePageQueryVO employeePageQueryVO = handleEmployeeToVO(record);
            // 添加至列表
            resultRecords.add(employeePageQueryVO);
        }

        return new PageResult(total, resultRecords);
    }



    /**
     * 修改员工信息
     * @param employeeUpdateDTO
     */
    @Override
    public void update(Long id, EmployeeUpdateDTO employeeUpdateDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeUpdateDTO, employee);

        // 添加ID
        employee.setEmployeeId(id);

        // 判断密码是否存在
        if(employee.getPassword() != null){
            employee.setPassword(DigestUtils.sha1Hex(employee.getPassword()));
        }
        
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateBy(BaseContext.getCurrentEmpId());

        employeeMapper.update(employee);
    }

    @Override
    public EmployeeUpdateVO getById(Long id) {
        Employee e = employeeMapper.getById(id);

//        EmployeePageQueryVO employeePageQueryVO = handleEmployeeToVO(e);
        EmployeeUpdateVO employeeUpdateVO = new EmployeeUpdateVO();
        // 复制属性
        BeanUtils.copyProperties(e, employeeUpdateVO);

        return employeeUpdateVO;
    }

    @Override
    public void deleteById(Long id) {
        employeeMapper.deleteById(id);
    }

    /**
     * 将Employee的信息补全
     * @param record
     * @return
     */
    private EmployeePageQueryVO handleEmployeeToVO(Employee record) {
        // 返回对象
        EmployeePageQueryVO employeePageQueryVO = new EmployeePageQueryVO();
        // 复制属性
        BeanUtils.copyProperties(record, employeePageQueryVO);

        // 处理国家信息
        String countryName = countryMapper.getNameByCountryId(record.getCountryId());
        employeePageQueryVO.setCountryName(countryName);

        // 处理性别信息
        Integer gender = record.getGender();
        if(gender.equals(GenderConstant.UNKNOWN)){
            employeePageQueryVO.setGender("未知");
        }else if(gender.equals(GenderConstant.MALE)){
            employeePageQueryVO.setGender("男");
        }else{
            employeePageQueryVO.setGender("女");
        }

        // 处理状态信息
        Integer status = record.getStatus();
        if(status.equals(StatusConstant.ENABLE)){
            employeePageQueryVO.setStatus("启用");
        }else{
            employeePageQueryVO.setStatus("禁用");
        }

        // 处理角色信息
        Integer role = record.getRoleId();
        employeePageQueryVO.setRoleName(roleMapper.getNameByRoleId(role));
        return employeePageQueryVO;
    }

}
