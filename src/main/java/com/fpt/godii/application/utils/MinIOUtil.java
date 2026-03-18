package com.fpt.godii.application.utils;

import com.fpt.godii.application.base.response.FileResponseDTO;
import com.fpt.godii.application.configurations.properties.MinIOConfiguration;
import com.fpt.godii.application.exception.FOSystemErrorException;
import io.minio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class MinIOUtil {
    private final MinioClient minioClient;
    private final List<String> bucketNames = new ArrayList<>();
    Logger logger = LoggerFactory.getLogger(MinIOUtil.class);

    public MinIOUtil(MinIOConfiguration configuration) {
        this.minioClient = MinioClient.builder()
                .endpoint(configuration.getEndpoint())
                .credentials(configuration.getAccessKey(), configuration.getSecretKey())
                .build();
    }

    public void createBucket(String bucketName) throws Exception {
        if (!this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
        bucketNames.add(bucketName);
    }

    public FileResponseDTO uploadFile(MultipartFile file, String bucketName) {
        if (null == file || 0 == file.getSize()) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String filePath = System.currentTimeMillis() + "/" + originalFilename;

        try {
            if (!bucketNames.contains(bucketName))
                createBucket(bucketName);

            this.minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucketName).object(filePath).stream(
                                    file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            assert originalFilename != null;
            Tika tika = new Tika();
            return new FileResponseDTO(originalFilename, tika.detect(filePath), bucketName + "/" + filePath, TimeUtil.now());

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new FOSystemErrorException("upload failed");
        }
    }

    public InputStream downloadFile(String urlPath) {
        try {
            String bucket = urlPath.split("/")[0];
            String relativePath = urlPath.substring(bucket.length());

            return minioClient.getObject(GetObjectArgs
                    .builder()
                    .bucket(bucket)
                    .object(relativePath)
                    .build());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new FOSystemErrorException("download failed");
        }
    }
}
