package com.yexca.controller.admin;

import com.yexca.dto.PersonalUserAddDTO;
import com.yexca.dto.PersonalUserPageQueryDTO;
import com.yexca.dto.PersonalUserUpdateDTO;
import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.PersonalUserService;
import com.yexca.vo.PersonalUserPageQueryVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user/personal")
@Slf4j
@Api(tags = "个人用户相关接口")
public class PersonalUserController {
    @Autowired
    private PersonalUserService personalUserService;

    /**
     * 增加个人用户
     * @param personalUserAddDTO
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody PersonalUserAddDTO personalUserAddDTO){
        log.info("增加用户：{}", personalUserAddDTO);
        personalUserService.add(personalUserAddDTO);
        return Result.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        log.info("删除个人用户ID：{}", id);
        personalUserService.deleteById(id);
        return Result.success();
    }

    /**
     * 个人用户分页查询
     * @param personalUserPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> pageQuery(PersonalUserPageQueryDTO personalUserPageQueryDTO){
        log.info("个人用户分页查询：{}", personalUserPageQueryDTO);
        PageResult pageResult = personalUserService.pageQuery(personalUserPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 个人用户修改
     * @param personalUserUpdateDTO
     * @return
     */
    @PutMapping("/edit")
    public Result update(@RequestBody PersonalUserUpdateDTO personalUserUpdateDTO){

    }
}
