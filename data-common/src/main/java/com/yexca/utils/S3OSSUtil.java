package com.yexca.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@Slf4j
public class S3OSSUtil {

    private S3Client s3Client;
    private String bucketName;
    private String endpoint;

    /**
     * 文件上传
     *
     * @param bytes 文件字节数组
     * @param objectName 对象名称
     * @return 文件的网络访问路径
     */
    public String upload(byte[] bytes, String objectName) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectName)
                    .build();

            // 上传文件
            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(bytes));

            // 文件访问路径拼接
            // 注意：不同云厂商的拼接规则可能不同。
            // 这里保留了你原本的 Virtual Hosted Style 逻辑 (https://bucketName.endpoint/objectName)
            String cleanEndpoint = endpoint.replace("https://", "").replace("http://", "");
            String fileUrl = "https://" + bucketName + "." + cleanEndpoint + "/" + objectName;

            // 如果你使用本地 Minio，可能需要改成 Path Style:
            // String fileUrl = "http://" + cleanEndpoint + "/" + bucketName + "/" + objectName;

            log.info("文件上传成功: {}", fileUrl);
            return fileUrl;

        } catch (S3Exception e) {
            log.error("S3 上传异常。错误代码: {}, 错误信息: {}", e.awsErrorDetails().errorCode(), e.awsErrorDetails().errorMessage());
            throw new RuntimeException("文件上传失败", e);
        }
    }

    /**
     * 文件删除
     *
     * @param url 文件的网络访问路径
     * @return 是否成功
     */
    public Boolean delete(String url) {
        String objectName = extractFullFilenameIfUUID(url);

        if (objectName == null) {
            log.warn("无法从 URL 解析出目标文件名，跳过删除操作: {}", url);
            return false;
        }

        try (S3Client s3Client = getS3Client()) {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectName)
                    .build();

            // 删除文件
            s3Client.deleteObject(deleteObjectRequest);
            log.info("文件删除成功: {}", objectName);
            return true;

        } catch (S3Exception e) {
            log.error("S3 删除异常。错误代码: {}, 错误信息: {}", e.awsErrorDetails().errorCode(), e.awsErrorDetails().errorMessage());
            return false;
        }
    }


    private String extractFullFilenameIfUUID(String url) {
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
