package com.glowbyte.task.api.v1;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.service.AppealPriceCalculator;
import com.glowbyte.task.service.impl.AppealStorageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/appeals")
public class AppealAPI {

    private final AppealPriceCalculator appealPriceCalculator;
    private final AppealStorageFacade appealStorageFacade;

    @Autowired
    public AppealAPI(AppealPriceCalculator appealPriceCalculator, AppealStorageFacade appealStorageFacade) {
        this.appealPriceCalculator = appealPriceCalculator;
        this.appealStorageFacade = appealStorageFacade;
    }

    @PostMapping("/calculate-total-price")
    public int calculatePrice(@RequestBody Appeal appeal) {
        return appealPriceCalculator.calculate(appeal);
    }

    @PostMapping
    public void save(@RequestBody Appeal appeal) {
        appealStorageFacade.store(appeal);
    }
}
