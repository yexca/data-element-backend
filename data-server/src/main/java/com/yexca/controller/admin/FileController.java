package com.yexca.controller.admin;

import com.yexca.constant.MessageConstant;
import com.yexca.result.Result;
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
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping
    public Result<String> upload(MultipartFile file){
        log.info("文件上传：{}", file);

        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 构建新文件名
            String objectName = UUID.randomUUID().toString() + extension;
            // 文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            //返回文件路径
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

    /**
     * 文件删除
     * @param file
     * @return
     */
    @DeleteMapping
    public Result delete(String file){
        log.info("文件删除：{}", file);
        aliOssUtil.delete(file);
//
        return Result.success();
    }

}
