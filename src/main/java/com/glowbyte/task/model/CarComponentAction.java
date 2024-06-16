package com.glowbyte.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.glowbyte.task.exception.InvariantException;
import lombok.Data;

import java.io.Serializable;

@Data
public class CarComponentAction implements Serializable {

    private String name;

    @JsonCreator
    public CarComponentAction(String name) {
        setName(name);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvariantException(400, "Car component action's name must be presented");
        }
        this.name = name;
    }
}
