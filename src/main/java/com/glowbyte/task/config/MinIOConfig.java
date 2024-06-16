package com.glowbyte.task.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinIOConfig {

    @Value("${min-io.storage.login}")
    private String login;

    @Value("${min-io.storage.password}")
    private String password;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("https://play.min.io")
                .credentials(login, password)
                .build();
    }
}
