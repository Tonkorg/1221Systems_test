package com._Systems.calorietracker.entity;


import com._Systems.calorietracker.ENUMs.Goal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @Email(message = "Неправильный email")
    @Column(unique = true)
    private String email;

    @Min(value = 1,message = "Минимальный возраст 1 год") @Max(value = 80, message = "Максимальный возраст не может больше 80 лет")
    private Integer age;

    @Min(value = 20, message = "Вес не может быть меньше 20 кг") @Max(value = 300, message = "Вес не может быть более 300 кг")
    private Double weight;

    @Min(value = 100, message = "Рост не может быть меньше 100 см") @Max(value = 250, message = "Рост не может быть более 250 см")
    private Double height;

    @Enumerated(EnumType.STRING)
    private Goal goal;

    private Double dailyCalorieNorm;
}
