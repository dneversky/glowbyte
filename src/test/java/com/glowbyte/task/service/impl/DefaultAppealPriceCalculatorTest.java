package com.glowbyte.task.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.glowbyte.task.util.Factory.appeal;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultAppealPriceCalculatorTest {

    @Autowired
    DefaultAppealPriceCalculator priceCalculator;

    @Test
    void calculate() {
        int result = priceCalculator.calculate(appeal());
        assertThat(result).isEqualTo(400);
    }
}