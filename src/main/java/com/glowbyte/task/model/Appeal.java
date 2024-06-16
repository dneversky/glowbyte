package com.glowbyte.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.glowbyte.task.exception.InvariantException;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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
}
