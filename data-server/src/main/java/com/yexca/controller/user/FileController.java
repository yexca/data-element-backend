package com.yexca.controller.user;

import com.yexca.constant.MessageConstant;
import com.yexca.result.Result;
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
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping
    public Result<String> upload(@RequestParam("file") MultipartFile file){
        log.info("文件上传：{}", file);

        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            extension = extension.toLowerCase();
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
        String fullFilename = extractFullFilenameIfUUID(file);
        if (fullFilename != null) {
            aliOssUtil.delete(fullFilename);
        } else {
            log.error("删除失败，文件名不是UUID格式");
            return Result.error("文件名不是UUID格式");
        }
//
        return Result.success();
    }

    public String extractFullFilenameIfUUID(String url) {
        // Extract filename from URL
        String[] parts = url.split("/");
        String filename = parts[parts.length - 1];

        // Check if the filename without the extension is a UUID
        String baseName = filename.contains(".") ? filename.substring(0, filename.lastIndexOf('.')) : filename;

        // Regex to match a UUID
        Pattern pattern = Pattern.compile("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(baseName);

        if (matcher.matches()) {
            return filename;  // Return the full filename if baseName is a UUID
        }

        return null;  // Return null if baseName is not a UUID
    }
}
