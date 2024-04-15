package com.yexca.controller.user;

import com.yexca.constant.JwtClaimsConstant;
import com.yexca.context.BaseContext;
import com.yexca.dto.EnterpriseUserLoginDTO;
import com.yexca.dto.EnterpriseUserUpdateDTO;
import com.yexca.entity.EnterpriseUser;
import com.yexca.properties.JwtProperties;
import com.yexca.result.Result;
import com.yexca.service.EnterpriseUserService;
import com.yexca.utils.JwtUtil;
import com.yexca.vo.EnterpriseDataPageQueryVO;
import com.yexca.vo.EnterpriseUserLoginVO;
import com.yexca.vo.EnterpriseUserPageQueryVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("userEnterpriseUserController")
@RequestMapping("/user/users/enterprise")
@Slf4j
@Api(tags = "企业用户相关接口")
public class EnterpriseUserController {
    @Autowired
    private EnterpriseUserService enterpriseUserService;
    @Autowired
    private JwtProperties jwtProperties;


    /**
     * 企业用户登录
     * @param enterpriseUserLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result<EnterpriseUserLoginVO> login(@RequestBody EnterpriseUserLoginDTO enterpriseUserLoginDTO){
        log.info("企业用户登录：{}", enterpriseUserLoginDTO);

        // 调用Service登录
        EnterpriseUser enterpriseUser = enterpriseUserService.login(enterpriseUserLoginDTO);

        // 登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, enterpriseUser.getUserId());
        claims.put(JwtClaimsConstant.CURRENT_ROLE_ID, enterpriseUser.getRoleId());

        // 构建token
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        // 构建返回vo
        EnterpriseUserLoginVO enterpriseUserLoginVO = EnterpriseUserLoginVO.builder()
                .userId(enterpriseUser.getUserId())
                .username(enterpriseUser.getUsername())
                .enterpriseName(enterpriseUser.getEnterpriseName())
                .role(enterpriseUser.getRoleId())
                .token(token)
                .build();

        return Result.success(enterpriseUserLoginVO);
    }

    /**
     * 获取企业信息
     * @return
     */
    @GetMapping
    public Result<EnterpriseUserPageQueryVO> getInfo(){
        Long currentUserId = BaseContext.getCurrentUserId();
        log.info("获取企业用户信息：{}", currentUserId);
        EnterpriseUserPageQueryVO enterpriseUserPageQueryVO = enterpriseUserService.getInfo(currentUserId);
        return Result.success(enterpriseUserPageQueryVO);
    }

    /**
     * 修改企业用户信息
     * @return
     */
    @PutMapping
    public Result upload(@RequestBody EnterpriseUserUpdateDTO enterpriseUserUpdateDTO){
        Long currentUserId = BaseContext.getCurrentUserId();
        log.info("修改企业ID：{}，信息：{}", currentUserId, enterpriseUserUpdateDTO);
        enterpriseUserService.update(currentUserId, enterpriseUserUpdateDTO);
        return Result.success();
    }
}
