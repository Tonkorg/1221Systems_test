package com._Systems.calorietracker.DTOs;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportDTO {
    private LocalDate date;
    private Double totalCalories;
    private List<MealDTO> meals;
}