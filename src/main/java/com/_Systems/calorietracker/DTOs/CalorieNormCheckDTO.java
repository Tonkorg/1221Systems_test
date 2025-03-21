package com._Systems.calorietracker.DTOs;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalorieNormCheckDTO {
    private LocalDate date;
    private Double totalCalories;
    private Double dailyNorm;
    private Boolean withinNorm;
}