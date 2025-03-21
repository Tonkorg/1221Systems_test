package com._Systems.calorietracker.service;

import com._Systems.calorietracker.DTOs.UserDTO;
import com._Systems.calorietracker.entity.User;
import com._Systems.calorietracker.exception.ResourceNotFoundException;
import com._Systems.calorietracker.mapper.UserMapper;
import com._Systems.calorietracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalorieCalculatorService calorieCalculatorService;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserDTO dto) {
        User user = userMapper.toEntity(dto);
        double dailyNorm = calorieCalculatorService.calculateDailyNorm(user);
        user.setDailyCalorieNorm(dailyNorm);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найден пользователь с id:  " + id));
    }


    public UserDTO getUserDtoById(Long id) {
        User user = findById(id);
        return userMapper.toDto(user);
    }
}