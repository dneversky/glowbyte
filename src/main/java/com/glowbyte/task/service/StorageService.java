package com.glowbyte.task.service;

public interface StorageService<T> {

    String store(T obj, String fileName);
}
