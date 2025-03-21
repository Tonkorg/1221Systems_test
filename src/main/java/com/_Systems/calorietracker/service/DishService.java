package com._Systems.calorietracker.service;

import com._Systems.calorietracker.DTOs.DishDTO;
import com._Systems.calorietracker.entity.Dish;
import com._Systems.calorietracker.exception.ResourceNotFoundException;
import com._Systems.calorietracker.mapper.DishMapper;
import com._Systems.calorietracker.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    public Dish createDish(DishDTO dto) {
        Dish dish = dishMapper.toEntity(dto);
        return dishRepository.save(dish);
    }

    public Dish findById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдено блюдо с id : " + id));
    }

    public DishDTO getDishDtoById(Long id) {
        Dish dish = findById(id);
        return dishMapper.toDto(dish);
    }
}