package com._Systems.calorietracker.mapper;

import com._Systems.calorietracker.DTOs.DishDTO;
import com._Systems.calorietracker.entity.Dish;
import org.springframework.stereotype.Component;

@Component
public class DishMapper {

    public Dish toEntity(DishDTO dto) {
        if (dto == null) {
            return null;
        }

        return Dish.builder()
                .id(dto.getId())
                .name(dto.getName())
                .calories(dto.getCalories())
                .protein(dto.getProtein())
                .fat(dto.getFat())
                .carbs(dto.getCarbs())
                .build();
    }

    public DishDTO toDto(Dish entity) {
        if (entity == null) {
            return null;
        }

        return DishDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .calories(entity.getCalories())
                .protein(entity.getProtein())
                .fat(entity.getFat())
                .carbs(entity.getCarbs())
                .build();
    }
}