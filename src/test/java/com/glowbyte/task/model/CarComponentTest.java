package com.glowbyte.task.model;

import com.glowbyte.task.exception.InvariantException;
import org.junit.jupiter.api.Test;

import static com.glowbyte.task.util.Factory.carComponentAction;
import static org.junit.jupiter.api.Assertions.*;

class CarComponentTest {

    @Test
    void create_invalid_carComponent() {
        CarComponent carComponent = new CarComponent();

        InvariantException exception = assertThrows(InvariantException.class, () -> carComponent.setName(null));
        assertEquals("Car component's name must be presented", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carComponent.setName(null));
        assertEquals("Car component's name must be presented", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carComponent.setPrice(null));
        assertEquals("Car component's price must not be null", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carComponent.setPrice(-1));
        assertEquals("Car component's price must not be less than 0", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carComponent.setCarComponentAction(null));
        assertEquals("Car component action must not be null", exception.getMessage());
    }

    @Test
    void create_valid_carComponent() {
        assertDoesNotThrow(() -> new CarComponent(
                "Дверь", 0, carComponentAction("ремонт"), "any comment"));
    }
}
