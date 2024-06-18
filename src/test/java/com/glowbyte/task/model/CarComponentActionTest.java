package com.glowbyte.task.model;

import com.glowbyte.task.exception.InvariantException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarComponentActionTest {

    @Test
    void create_invalid_car_component_action() {
        CarComponentAction action = new CarComponentAction();

        InvariantException exception = assertThrows(InvariantException.class, () -> action.setName(null));
        assertEquals("Car component action's name must be presented", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> action.setName(""));
        assertEquals("Car component action's name must be presented", exception.getMessage());
    }

    @Test
    void create_valid_car_component_action() {
        assertDoesNotThrow(() -> new CarComponentAction("ремонт"));
    }
}
