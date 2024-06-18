package com.glowbyte.task.model;

import com.glowbyte.task.exception.InvariantException;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.glowbyte.task.util.Factory.appeal;
import static com.glowbyte.task.util.Factory.carArea;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;

class AppealTest {

    @Test
    void create_invalid_appeal() {
        Appeal appeal = new Appeal();

        InvariantException exception = assertThrows(InvariantException.class, () -> appeal.setCarAreas(null));
        assertEquals("Car areas must not be null", exception.getMessage());

        exception = assertThrows(InvariantException.class, () -> appeal.setCarAreas(anySet()));
        assertEquals("Car areas' size must be more than 0", exception.getMessage());
    }

    @Test
    void create_valid_appeal() {
        assertDoesNotThrow(() -> new Appeal(Set.of(carArea("Кузов"))));
    }

    @Test
    void to_string() {
        assertDoesNotThrow(() -> appeal().toString());
    }
}
