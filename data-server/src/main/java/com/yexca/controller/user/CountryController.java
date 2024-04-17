package com.yexca.controller.user;

import com.yexca.result.Result;
import com.yexca.service.CountryService;
import com.yexca.vo.CountryListVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("userCountryController")
@RequestMapping("/user/country")
@Slf4j
@Api(tags = "国家相关接口")
public class CountryController {
    @Autowired
    private CountryService countryService;

    /**
     * 获取全部国家
     * @return
     */
    @GetMapping("/list")
    public Result<List<CountryListVO>> list(){
        log.info("获取全部国家");
        List<CountryListVO> countryListVO = countryService.list();
        return Result.success(countryListVO);
    }
}
