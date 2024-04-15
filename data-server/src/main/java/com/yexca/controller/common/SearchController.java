package com.yexca.controller.common;

import com.yexca.result.PageResult;
import com.yexca.result.Result;
import com.yexca.service.SearchService;
import com.yexca.vo.DataSearchVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
@Slf4j
@Api(tags = "用户搜索接口")
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 搜索
     * @param kw
     * @return
     */
    @GetMapping
    public Result<PageResult> search(String kw){
        log.info("搜索内容：{}", kw);
        PageResult pageResult = searchService.search(kw);
        return Result.success(pageResult);
    }
}
