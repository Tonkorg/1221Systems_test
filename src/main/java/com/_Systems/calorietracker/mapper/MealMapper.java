package com._Systems.calorietracker.mapper;

import com._Systems.calorietracker.DTOs.MealDTO;
import com._Systems.calorietracker.entity.Dish;
import com._Systems.calorietracker.entity.Meal;
import com._Systems.calorietracker.entity.User;
import com._Systems.calorietracker.service.DishService;
import com._Systems.calorietracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MealMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private DishService dishService;

    /**
     * Преобразует MealDTO в Meal, заполняя связи с User и Dish.
     * @param dto Входной DTO
     * @return Сущность Meal
     */
    public Meal toEntity(MealDTO dto) {
        if (dto == null) {
            return null;
        }

        // Создаем базовый объект Meal
        Meal meal = Meal.builder()
                .id(dto.getId())
                .dateTime(dto.getDateTime())
                .build();

        // Заполняем связи
        if (dto.getUserId() != null) {
            User user = userService.findById(dto.getUserId());
            meal.setUser(user);
        }

        if (dto.getDishIds() != null && !dto.getDishIds().isEmpty()) {
            List<Dish> dishes = dto.getDishIds().stream()
                    .map(dishService::findById)
                    .collect(Collectors.toList());
            meal.setDishes(dishes);
        }

        return meal;
    }

    /**
     * Преобразует Meal в MealDTO.
     * @param entity Входная сущность
     * @return DTO MealDTO
     */
    public MealDTO toDto(Meal entity) {
        if (entity == null) {
            return null;
        }

        return MealDTO.builder()
                .id(entity.getId())
                .userId(entity.getUser() != null ? entity.getUser().getId() : null)
                .dateTime(entity.getDateTime())
                .dishIds(entity.getDishes() != null ?
                        entity.getDishes().stream()
                                .map(Dish::getId)
                                .collect(Collectors.toList()) :
                        null)
                .build();
    }
}