package com.glowbyte.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.glowbyte.task.exception.InvariantException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class Appeal {

    private Set<CarArea> carAreas = new HashSet<>();

    @JsonCreator
    public Appeal(Set<CarArea> carAreas) {
        setCarAreas(carAreas);
    }

    public void setCarAreas(Set<CarArea> carAreas) {
        if (carAreas == null) {
            throw new InvariantException(400, "Car areas must not be null");
        } else if (carAreas.size() == 0) {
            throw new InvariantException(400, "Car areas' size must be more than 0");
        }
        this.carAreas = carAreas;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CarArea carArea : getCarAreas()) {
            sb.append("Ремонтная зона: ").append(carArea.getName()).append("\n").append("---\n");
            int i = 1;
            for (CarComponent carComponent : carArea.getCarComponents()) {
                sb.append("Элемент автомобиля: ").append(carComponent.getName()).append("\n");
                sb.append("Стоимость: ").append(carComponent.getPrice()).append("\n");
                sb.append("Комментарий: ").append(carComponent.getComment()).append("\n");
                sb.append("Действие: ").append(carComponent.getCarComponentAction().getName()).append("\n");
                if (i != carArea.getCarComponents().size()) sb.append("---\n");
                i++;
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
