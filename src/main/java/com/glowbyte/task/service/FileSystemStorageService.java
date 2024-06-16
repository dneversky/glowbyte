package com.glowbyte.task.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Primary
@Service
public class FileSystemStorageService implements StorageService<String> {

    @Value("${storageDirectory}")
    private String storageDirectory;

    public String store(String obj, String fileName) {
        String todayDirectory = storageDirectory + "/" + LocalDate.now();
        createDirectory(todayDirectory);
        File file;
        try {
            file = new File(todayDirectory, fileName + ".txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(obj);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return file.getAbsolutePath();
    }

    private boolean storageDirectoryExists(String location) {
        return Paths.get(location).toFile().exists();
    }

    private void createDirectory(String location) {
        if (!storageDirectoryExists(location)) {
            try {
                Files.createDirectory(Path.of(location));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @PostConstruct
    private void init() {
        if (storageDirectory.isBlank()) {
            storageDirectory = this.getClass().getClassLoader().getResource(".").getPath().substring(1);
        } else {
            createDirectory(storageDirectory);
        }
    }
}
