package com.glowbyte.task.service.impl;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.service.AppealPriceCalculator;
import com.glowbyte.task.service.LocalStorageService;
import com.glowbyte.task.service.RemoteStorageService;
import com.glowbyte.task.service.StorageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class AppealStorageFacade implements StorageFacade<Appeal> {

    private final LocalStorageService<String> localStorageService;
    private final RemoteStorageService remoteStorageService;
    private final AppealPriceCalculator appealPriceCalculator;

    public void store(Appeal appeal) {
        String filePath = localStorageService.store(appeal.toString(), generateFileName(appeal));
        String remoteFileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        remoteStorageService.store(filePath, remoteFileName);
    }

    private String generateFileName(Appeal appeal) {
        int totalPrice = appealPriceCalculator.calculate(appeal);
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        return totalPrice + "-" + time.toString().replace(":", "-");
    }
}
