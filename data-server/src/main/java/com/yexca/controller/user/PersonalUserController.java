package com.yexca.controller.user;

import com.yexca.constant.JwtClaimsConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.PersonalLoginDTO;
import com.yexca.dto.PersonalUserUpdateDTO;
import com.yexca.entity.Employee;
import com.yexca.entity.PersonalUser;
import com.yexca.properties.JwtProperties;
import com.yexca.result.Result;
import com.yexca.service.PersonalUserService;
import com.yexca.utils.JwtUtil;
import com.yexca.vo.EmployeeLoginVO;
import com.yexca.vo.PersonalUserLoginVO;
import com.yexca.vo.PersonalUserPageQueryVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("userPersonalUserController")
@RequestMapping("/user/users/personal")
@Slf4j
@Api(tags = "个人用户相关接口")
public class PersonalUserController {
    @Autowired
    private PersonalUserService personalUserService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 个人用户登录
     * @param personalLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<PersonalUserLoginVO> login(@RequestBody PersonalLoginDTO personalLoginDTO){
        log.info("个人用户登录：{}", personalLoginDTO);

        // 调用service登录
        PersonalUser personalUser = personalUserService.login(personalLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, personalUser.getUserId());
        claims.put(JwtClaimsConstant.CURRENT_ROLE_ID, personalUser.getRoleId());

        // 构建token
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        // 构建返回vo
        PersonalUserLoginVO personalUserLoginVO = PersonalUserLoginVO.builder()
                .userId(personalUser.getUserId())
                .username(personalUser.getUsername())
                .nickname(personalUser.getNickname())
                .role(personalUser.getRoleId())
                .token(token)
                .build();

        return Result.success(personalUserLoginVO);

    }

    /**
     * 获取个人信息
     * @param
     * @return
     */
    @GetMapping("/{id}")
    public Result<PersonalUserPageQueryVO> getInfo(@PathVariable Long id){
        Long currentUserId = BaseContext.getCurrentUserId();
        log.info("个人{}获取个人用户信息ID：{}", currentUserId, id);
        PersonalUserPageQueryVO personalUserPageQueryVO = personalUserService.getInfo(id);
        return Result.success(personalUserPageQueryVO);
    }

    /**
     * 修改个人信息
     * @return
     */
    @PutMapping
    public Result upload(@RequestBody PersonalUserUpdateDTO personalUserUpdateDTO){
        Long currentUserId = BaseContext.getCurrentUserId();
        log.info("修改个人ID：{}，信息：{}", currentUserId, personalUserUpdateDTO);
        personalUserService.update(currentUserId, personalUserUpdateDTO);
        return Result.success();
    }
}
