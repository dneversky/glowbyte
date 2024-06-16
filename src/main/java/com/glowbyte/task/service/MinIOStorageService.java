package com.glowbyte.task.service;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MinIOStorageService {

    @Value("${min-io.bucket-name}")
    private String bucketName;

    private final MinioClient minioClient;

    @Autowired
    public MinIOStorageService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    public void store(String localFilePath, String remoteFileName) {
        uploadFile(localFilePath, remoteFileName);
    }

    private void uploadFile(String localFilePath, String remoteFileName) {
        try {
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(bucketName)
                    .object(remoteFileName)
                    .filename(localFilePath)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("File {} successfully uploaded.", remoteFileName);
    }

    private void createBucket() {
        try {
            boolean exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!exists) minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("Bucket {} has been successfully initialized.", bucketName);
    }

    @PostConstruct
    private void init() {
        createBucket();
    }
}
