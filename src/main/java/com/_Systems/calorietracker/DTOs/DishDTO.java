package com._Systems.calorietracker.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class DishDTO {
    private Long id;
    @NotBlank(message = "Необходимо наименование блюда")
    private String name;
    @Min(value = 0, message = "Не может быть отрицательных калорий")
    private Double calories;
    @Min(value = 0, message = "Белки не могут быть меньше 0")
    private Double protein;
    @Min(value = 0, message = "Жиры не могут быть меньше 0")
    private Double fat;
    @Min(value = 0, message = "Углеводы не могут быть мешьше 0")
    private Double carbs;
}