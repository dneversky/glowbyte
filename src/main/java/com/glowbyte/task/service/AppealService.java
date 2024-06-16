package com.glowbyte.task.service;

import com.glowbyte.task.model.Appeal;

public interface AppealService {

    /**
     * Method calculates a total price of the given appeal.
     */
    int calculateTotalPrice(Appeal appeal);

    /**
     * Method for saving Appeals into local storage.
     * Set the storageDirectory property in application.yaml
     * in order to specify where to store files in the local system.
     */
    String save(Appeal appeal);
}
