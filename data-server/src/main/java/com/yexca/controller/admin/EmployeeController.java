package com.yexca.controller.admin;

import com.yexca.constant.JwtClaimsConstant;
import com.yexca.dto.EmployeeAddDTO;
import com.yexca.dto.EmployeeLoginDTO;
import com.yexca.dto.EmployeePageQueryDTO;
import com.yexca.dto.EmployeeUpdateDTO;
import com.yexca.entity.Employee;
import com.yexca.properties.JwtProperties;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.EmployeeService;
import com.yexca.utils.JwtUtil;
import com.yexca.vo.EmployeeLoginVO;
import com.yexca.vo.EmployeeUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        // 调用service登录
        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMPLOYEE_ID, employee.getEmployeeId());

        // 构建token
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        // 构建返回vo
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .employeeId(employee.getEmployeeId())
                .username(employee.getUsername())
                .nickname(employee.getNickname())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 新增员工
     * @param employeeAddDTO
     * @return
     */
    @PostMapping
    public Result add(@RequestBody EmployeeAddDTO employeeAddDTO){
        log.info("新增员工：{}",employeeAddDTO);
        employeeService.add(employeeAddDTO);
        return Result.success();
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询：{}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改员工
     * @param employeeUpdateDTO
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody EmployeeUpdateDTO employeeUpdateDTO){
        log.info("修改员工信息ID：{}，信息：{}", id, employeeUpdateDTO);
        employeeService.update(id, employeeUpdateDTO);
        return Result.success();
    }

    /**
     * 通过ID获取信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<EmployeeUpdateVO> getById(@PathVariable Long id){
        log.info("根据id获取员工：{}", id);
        EmployeeUpdateVO employeeUpdateVO = employeeService.getById(id);
        return Result.success(employeeUpdateVO);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除员工ID：{}", id);
        employeeService.deleteById(id);
        return Result.success();
    }
}
