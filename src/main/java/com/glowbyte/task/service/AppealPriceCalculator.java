package com.glowbyte.task.service;

import com.glowbyte.task.model.Appeal;

public interface AppealPriceCalculator {

    /**
     * Method calculates a total price of the given appeal.
     */
    int calculate(Appeal appeal);
}
