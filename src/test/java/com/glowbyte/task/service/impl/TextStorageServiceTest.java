package com.glowbyte.task.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.glowbyte.task.util.Factory.appeal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TextStorageServiceTest {

    @Autowired
    TextStorageService storageService;

    @Test
    void store() {
        assertDoesNotThrow(() -> storageService.store(appeal().toString(), "400.txt"));
    }
}
