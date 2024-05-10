package com.yexca.controller.user;

import com.yexca.constant.MessageConstant;
import com.yexca.context.BaseContext;
import com.yexca.result.Result;
import com.yexca.service.FileService;
import com.yexca.utils.AliOssUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController("userFileController")
@RequestMapping("/user/file")
@Slf4j
@Api(tags = "文件接口")
public class FileController {
    @Autowired
    private FileService fileService;


    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile file){
        log.info("用户{}，文件上传：{}", BaseContext.getCurrentUserId(), file);

        String filePath = fileService.upload(file);
        return Result.success(filePath);
    }

    /**
     * 文件删除
     * @param file
     * @return
     */
    @DeleteMapping
    public Result delete(String file){
        log.info("用户{}，文件删除：{}", BaseContext.getCurrentUserId(), file);

        fileService.delete(file);
        return Result.success();
    }
}
