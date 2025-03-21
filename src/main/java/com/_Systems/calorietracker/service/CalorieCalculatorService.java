package com._Systems.calorietracker.service;

import com._Systems.calorietracker.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CalorieCalculatorService {



    public double calculateDailyNorm(User user) {


        if (user.getAge() == null || user.getWeight() == null || user.getHeight() == null || user.getGoal() == null) {
            throw new IllegalArgumentException("Все параметры пользователя (возраст, вес, рост, цель) должны быть указаны.");
        }

        double bmr = 88.362 + (13.397 * user.getWeight()) +
                (4.799 * user.getHeight()) - (5.677 * user.getAge());

        return switch (user.getGoal()) {
            case WEIGHT_LOSS -> bmr * 0.85;
            case WEIGHT_GAIN -> bmr * 1.15;
            default -> bmr;
        };
    }
}