package com.yexca.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    String upload(MultipartFile file);

    /**
     * 文件删除
     * @param file
     */
    void delete(String file);
}
