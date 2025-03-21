package com._Systems.calorietracker.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class MealDTO {
    private Long id;
    private Long userId;
    private LocalDateTime dateTime;
    @NotEmpty(message = "Список блюд не может быть пуст!")
    private List<Long> dishIds;
}