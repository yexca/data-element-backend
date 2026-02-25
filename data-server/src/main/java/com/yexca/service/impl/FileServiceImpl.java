package com.yexca.service.impl;

import com.yexca.constant.MessageConstant;
import com.yexca.exception.FileDeleteFailException;
import com.yexca.exception.FileUploadFailException;
import com.yexca.result.Result;
import com.yexca.service.FileService;
import com.yexca.utils.S3OSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private S3OSSUtil s3OSSUtil;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @Override
    public String upload(MultipartFile file) {
        try {
            // 获取原始文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件名后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            extension = extension.toLowerCase();
            // 构建新文件名
            String objectName = UUID.randomUUID().toString() + extension;
            // 文件请求路径
            String filePath = s3OSSUtil.upload(file.getBytes(), objectName);
            //返回文件路径
            return filePath;
        } catch (IOException e) {
            throw new FileUploadFailException(MessageConstant.UPLOAD_FAILED);
        }
    }

    @Override
    public void delete(String file) {
        String fullFilename = extractFullFilenameIfUUID(file);
        if (fullFilename != null) {
            s3OSSUtil.delete(fullFilename);
        } else {
            throw new FileDeleteFailException(MessageConstant.DELETE_FAILED);
//            log.error("删除失败，文件名不是UUID格式");
//            return Result.error("文件名不是UUID格式");
        }
//        return Result.success();
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
