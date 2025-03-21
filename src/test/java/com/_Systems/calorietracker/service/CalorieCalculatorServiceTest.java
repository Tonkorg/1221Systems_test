package com._Systems.calorietracker.service;

import com._Systems.calorietracker.ENUMs.Goal;
import com._Systems.calorietracker.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalorieCalculatorServiceTest {

    private CalorieCalculatorService calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalorieCalculatorService();
    }

    @Test
    void testCalculateDailyNorm_Maintenance() {
        User user = User.builder()
                .age(30)
                .weight(70.0)
                .height(170.0)
                .goal(Goal.MAINTENANCE)
                .build();

        double norm = calculator.calculateDailyNorm(user);
        assertEquals(1671.672, norm, 0.01); // Обновленное значение
    }

    @Test
    void testCalculateDailyNorm_WeightLoss() {
        User user = User.builder()
                .age(30)
                .weight(70.0)
                .height(170.0)
                .goal(Goal.WEIGHT_LOSS)
                .build();

        double norm = calculator.calculateDailyNorm(user);
        assertEquals(1420.92, norm, 0.01); // -15% от базовой нормы
    }

    @Test
    void testCalculateDailyNorm_InvalidData() {
        User user = User.builder()
                .age(null) // Отсутствует возраст
                .weight(70.0)
                .height(170.0)
                .goal(Goal.MAINTENANCE)
                .build();

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateDailyNorm(user));
    }
}