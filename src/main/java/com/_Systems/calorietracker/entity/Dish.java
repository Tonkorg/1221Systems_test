package com._Systems.calorietracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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