package com.yexca.mapper;

import com.github.pagehelper.Page;
import com.yexca.dto.EmployeePageQueryDTO;
import com.yexca.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * 插入员工
     * @param employee
     */
    @Insert("insert into employee(username, password, nickname, email, phone, gender, country_id, nin, status, role_id, create_by, create_time, update_time, update_by) " +
            "VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{gender}, #{countryId}, #{nin}, #{status}, #{roleId}, #{createBy}, #{createTime}, #{updateTime}, #{updateBy})")
    void insert(Employee employee);

    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工信息
     * @param employee
     */
    void update(Employee employee);

    @Select("select * from employee where employee_id = #{id}")
    Employee getById(Long id);
}
