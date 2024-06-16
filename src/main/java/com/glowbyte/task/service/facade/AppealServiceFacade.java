package com.glowbyte.task.service.facade;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.service.AppealService;
import com.glowbyte.task.service.MinIOStorageService;
import org.springframework.stereotype.Service;

@Service
public class AppealServiceFacade {

    private final MinIOStorageService minIOStorageService;
    private final AppealService appealService;

    public AppealServiceFacade(AppealService appealService, MinIOStorageService minIOStorageService) {
        this.appealService = appealService;
        this.minIOStorageService = minIOStorageService;
    }

    public void save(Appeal appeal) {
        String filePath = appealService.save(appeal);
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        minIOStorageService.store(filePath, fileName);
    }
}
