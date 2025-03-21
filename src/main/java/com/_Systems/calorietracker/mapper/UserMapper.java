package com._Systems.calorietracker.mapper;

import com._Systems.calorietracker.DTOs.UserDTO;
import com._Systems.calorietracker.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User entity) {
        if (entity == null) {
            return null;
        }

        return UserDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .age(entity.getAge())
                .weight(entity.getWeight())
                .height(entity.getHeight())
                .goal(entity.getGoal())
                .dailyCalorieNorm(entity.getDailyCalorieNorm())
                .build();
    }


    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .weight(dto.getWeight())
                .height(dto.getHeight())
                .goal(dto.getGoal())
                .dailyCalorieNorm(dto.getDailyCalorieNorm())
                .build();
    }
}