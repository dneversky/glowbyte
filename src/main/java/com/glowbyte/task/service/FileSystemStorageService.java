package com.glowbyte.task.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;

@Primary
@Service
public class FileSystemStorageService implements StorageService<String> {

    @Value("${storageDirectory}")
    private String storageDirectory;

    public String store(String obj, String fileName) {
        File todayDirectory = new File(storageDirectory, LocalDate.now().toString());
        todayDirectory.mkdirs();
        File file = new File(todayDirectory, fileName + ".txt");
        try {
            Files.writeString(file.toPath(), obj, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file.getAbsolutePath();
    }

    @PostConstruct
    private void init() {
        if (storageDirectory.isBlank()) {
            storageDirectory = this.getClass().getClassLoader().getResource(".").getPath().substring(1);
        }
    }
}
