package com.glowbyte.task.service;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.model.CarArea;
import com.glowbyte.task.model.CarComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
public class DefaultAppealService implements AppealService {

    private final StorageService<String> storageService;

    @Autowired
    public DefaultAppealService(StorageService<String> storageService) {
        this.storageService = storageService;
    }

    @Override
    public int calculateTotalPrice(Appeal appeal) {
        return appeal.getCarAreas().stream()
                .flatMap(e -> e.getCarComponents().stream())
                .map(CarComponent::getPrice)
                .reduce(Integer::sum)
                .orElseThrow(() -> new RuntimeException("Произошла ошибка во время подсчета стоимости работ."));
    }

    @Override
    public String save(Appeal appeal) {
        int totalPrice = calculateTotalPrice(appeal);
        LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
        String fileName = totalPrice + "-" + time.toString().replace(":", "-");
        return storageService.store(parseAppealIntoString(appeal), fileName);
    }

    private String parseAppealIntoString(Appeal appeal) {
        StringBuilder sb = new StringBuilder();
        for (CarArea carArea : appeal.getCarAreas()) {
            sb.append("Ремонтная зона: ").append(carArea.getName()).append("\n");
            for (CarComponent carComponent : carArea.getCarComponents()) {
                sb.append("Элемент автомобиля: ").append(carComponent.getName()).append("\n");
                sb.append("Стоимость: ").append(carComponent.getPrice()).append("\n");
                sb.append("Комментарий: ").append(carComponent.getComment()).append("\n");
                sb.append("Действие: ").append(carComponent.getCarComponentAction().getName());
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
