package com._Systems.calorietracker.service;

import com._Systems.calorietracker.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CalorieCalculatorService {



    public double calculateDailyNorm(User user) {


        if (user.getAge() == null || user.getWeight() == null || user.getHeight() == null || user.getGoal() == null) {
            throw new IllegalArgumentException("All user parameters (age, weight, height, goal) must be provided");
        }

        double bmr = 447.593 + (9.247 * user.getWeight()) +
                (3.098 * user.getHeight()) - (4.330 * user.getAge());

        return switch (user.getGoal()) {
            case WEIGHT_LOSS -> bmr * 0.85;
            case WEIGHT_GAIN -> bmr * 1.15;
            default -> bmr;
        };
    }
}