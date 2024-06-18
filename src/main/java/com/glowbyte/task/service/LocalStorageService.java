package com.glowbyte.task.service;

public interface LocalStorageService <T> {

    String store(T source, String localFileName);
}
