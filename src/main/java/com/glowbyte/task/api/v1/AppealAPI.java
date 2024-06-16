package com.glowbyte.task.api.v1;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.service.AppealService;
import com.glowbyte.task.service.facade.AppealServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appeals")
public class AppealAPI {

    private final AppealService appealService;
    private final AppealServiceFacade appealServiceFacade;

    @Autowired
    public AppealAPI(AppealService appealService, AppealServiceFacade appealServiceFacade) {
        this.appealService = appealService;
        this.appealServiceFacade = appealServiceFacade;
    }

    @PostMapping("/calculate-total-price")
    public int calculatePrice(@RequestBody Appeal appeal) {
        return appealService.calculateTotalPrice(appeal);
    }

    @PostMapping
    public void save(@RequestBody Appeal appeal) {
        appealServiceFacade.save(appeal);
    }
}
