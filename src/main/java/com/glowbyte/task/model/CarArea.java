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
public class CarArea {

    private String name;
    private Set<CarComponent> carComponents = new HashSet<>();

    @JsonCreator
    public CarArea(String name, Set<CarComponent> carComponents) {
        setName(name);
        setCarComponents(carComponents);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvariantException(400, "Name of the car area must be presented");
        }
        this.name = name;
    }

    public void setCarComponents(Set<CarComponent> carComponents) {
        if (carComponents == null) {
            throw new InvariantException(400, "Car components must not be null");
        } else if (carComponents.size() == 0) {
            throw new InvariantException(400, "Car components' size must be more than 0");
        }
        this.carComponents = carComponents;
    }
}
