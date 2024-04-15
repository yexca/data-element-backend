package com.yexca.controller.common;

import com.yexca.result.Result;
import com.yexca.service.PersonalDataService;
import com.yexca.vo.PersonalDataCommonVO;
import com.yexca.vo.PersonalDataPageQueryVO;
import com.yexca.vo.PersonalDataUpdateVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("commonPersonalDataController")
@RequestMapping("/data/personal")
@Slf4j
@Api(tags = "公共数据相关接口")
public class personalDataController {
    @Autowired
    private PersonalDataService personalDataService;

    /**
     * 根据Id获取信息
     * @param dataId
     * @return
     */
    @GetMapping("/{dataId}")
    public Result<PersonalDataCommonVO> getCommonById(@PathVariable Long dataId){
        log.info("获取个人数据信息ID：{}", dataId);
        PersonalDataCommonVO personalDataCommonVO = personalDataService.getCommonById(dataId);
        return Result.success(personalDataCommonVO);
    }
}
