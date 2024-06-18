package com.glowbyte.task.model;

import com.glowbyte.task.exception.InvariantException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.glowbyte.task.util.Factory.carComponent;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;

class CarAreaTest {

    @Test
    void create_invalid_carArea() {
        CarArea carArea = new CarArea();

        InvariantException exception = assertThrows(InvariantException.class, () -> carArea.setName(null));
        assertEquals("Name of the car area must be presented", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carArea.setName(""));
        assertEquals("Name of the car area must be presented", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carArea.setCarComponents(null));
        assertEquals("Car components must not be null", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> carArea.setCarComponents(anySet()));
        assertEquals("Car components' size must be more than 0", exception.getMessage());
    }

    @Test
    void create_valid_carArea() {
        assertDoesNotThrow(() -> new CarArea("Кузов", Set.of(carComponent("Дверь"))));
    }
}
