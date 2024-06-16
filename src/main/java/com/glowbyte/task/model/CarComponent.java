package com.glowbyte.task.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.glowbyte.task.exception.InvariantException;
import lombok.Data;

@Data
public class CarComponent {

    private String name;
    private int price;
    private String comment;
    private CarComponentAction carComponentAction;

    @JsonCreator
    public CarComponent(String name, int price, CarComponentAction carComponentAction, String comment) {
        setName(name);
        setPrice(price);
        setCarComponentAction(carComponentAction);
        setComment(comment);
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvariantException(400, "Car component's name must be presented");
        }
        this.name = name;
    }

    public void setPrice(Integer price) {
        if (price == null) {
            throw new InvariantException(400, "Car component's price must not be null");
        } else if (price < 0) {
            throw new InvariantException(400, "Car component's price must not be less than 0");
        }
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCarComponentAction(CarComponentAction carComponentAction) {
        if (carComponentAction == null) {
            throw new InvariantException(400, "Car component action must not be null");
        }
        this.carComponentAction = carComponentAction;
    }
}
