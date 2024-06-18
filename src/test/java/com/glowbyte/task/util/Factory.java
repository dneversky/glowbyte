package com.glowbyte.task.util;

import com.glowbyte.task.model.Appeal;
import com.glowbyte.task.model.CarArea;
import com.glowbyte.task.model.CarComponent;
import com.glowbyte.task.model.CarComponentAction;

import java.util.Set;

public class Factory {

    public static Appeal appeal() {
        return new Appeal(Set.of(carArea("Кузов"), carArea("Ходовая")));
    }

    public static CarArea carArea(String name) {
        return new CarArea(name, Set.of(carComponent("Дверь"), carComponent("Колесо")));
    }

    public static CarComponent carComponent(String name) {
        return new CarComponent(name, 100, carComponentAction("ремонт"), "any comment");
    }

    public static CarComponentAction carComponentAction(String name) {
        return new CarComponentAction(name);
    }
}
