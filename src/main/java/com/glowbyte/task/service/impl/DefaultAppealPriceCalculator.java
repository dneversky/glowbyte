package com.glowbyte.task.service.impl;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.model.CarComponent;
import com.glowbyte.task.service.AppealPriceCalculator;
import org.springframework.stereotype.Service;

@Service
public class DefaultAppealPriceCalculator implements AppealPriceCalculator {

    @Override
    public int calculate(Appeal appeal) {
        return appeal.getCarAreas().stream()
                .flatMap(e -> e.getCarComponents().stream())
                .map(CarComponent::getPrice)
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("Произошла ошибка во время подсчета стоимости работ."));
    }
}
