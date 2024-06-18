package com.glowbyte.task.service;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.model.CarArea;
import com.glowbyte.task.model.CarComponent;
import com.glowbyte.task.model.CarComponentAction;
import com.glowbyte.task.service.impl.AppealStorageFacade;
import com.glowbyte.task.service.impl.DefaultAppealPriceCalculator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DefaultAppealStorageTest {

    private final Appeal appeal = buildAppeal();

    @Mock
    private AppealStorageFacade appealStorageFacade;

    @InjectMocks
    private DefaultAppealPriceCalculator appealPriceCalculator;

    @Test
    void calculateTotalPrice() {
        int totalPrice = appealPriceCalculator.calculate(appeal);
        assertEquals(4, totalPrice);
    }

    @Test
    void save() {
        assertDoesNotThrow(() -> appealStorageFacade.store(appeal));
    }

    private Appeal buildAppeal() {
        Set<CarArea> carAreas = new HashSet<>();

        Set<CarComponent> carComponents = new HashSet<>();
        carComponents.add(new CarComponent("Правое переднее стекло", 1, new CarComponentAction("Замена"), "ok"));
        carComponents.add(new CarComponent("Передняя правая дверь", 1, new CarComponentAction("Ремонт"), "ok"));

        CarArea carArea1 = new CarArea("Кузов", carComponents);
        CarArea carArea2 = new CarArea("Зад", carComponents);

        carAreas.add(carArea1);
        carAreas.add(carArea2);

        return new Appeal(carAreas);
    }
}