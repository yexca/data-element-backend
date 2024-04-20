package com.yexca.service;

import com.yexca.dto.EmployeeAddDTO;
import com.yexca.dto.EmployeeLoginDTO;
import com.yexca.dto.EmployeePageQueryDTO;
import com.yexca.dto.EmployeeUpdateDTO;
import com.yexca.entity.Employee;
import com.yexca.result.PageResult;
import com.yexca.vo.EmployeePageQueryVO;
import com.yexca.vo.EmployeeUpdateVO;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 增加员工
     * @param employeeAddDTO
     */
    void add(EmployeeAddDTO employeeAddDTO);

    /**
     * 分页查询员工信息
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工信息
     * @param employeeUpdateDTO
     */
    void update(Long id, EmployeeUpdateDTO employeeUpdateDTO);

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    EmployeeUpdateVO getById(Long id);

    /**
     * 根据ID删除员工
     * @param id
     */
    void deleteById(Long id);

    /**
     * 获取个人信息
     * @param currentEmpId
     * @return
     */
    EmployeePageQueryVO getMyself(Long currentEmpId);
}
