package com.yexca.controller.admin;

import com.yexca.constant.MessageConstant;
import com.yexca.context.BaseContext;
import com.yexca.exception.BaseException;
import com.yexca.result.Result;
import com.yexca.service.FileService;
import com.yexca.utils.AliOssUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController("adminFileController")
@RequestMapping("/admin/file")
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
    public Result<String> upload(MultipartFile file){
        log.info("员工{}，文件上传：{}", BaseContext.getCurrentEmpId(), file);

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
        log.info("员工{}，文件删除：{}", BaseContext.getCurrentEmpId(), file);

        fileService.delete(file);
        return Result.success();
    }

}
