package com._Systems.calorietracker.DTOs;

import com._Systems.calorietracker.ENUMs.Goal;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;


    @Email(message = "Неверный email")
    private String email;

    @Min(value = 1,message = "Минимальный возраст 1 год") @Max(value = 80, message = "Максимальный возраст не может больше 80 лет")
    private Integer age;
    @Min(value = 20, message = "Вес не может быть меньше 20 кг") @Max(value = 300, message = "Вес не может быть более 300 кг")
    private Double weight;
    @Min(value = 100, message = "Рост не может быть меньше 100 см") @Max(value = 250, message = "Рост не может быть более 250 см")
    private Double height;
    @NotNull
    private Goal goal;
    private Double dailyCalorieNorm;
}