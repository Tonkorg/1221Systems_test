package com._Systems.calorietracker.service;

import com._Systems.calorietracker.DTOs.CalorieNormCheckDTO;
import com._Systems.calorietracker.DTOs.DailyReportDTO;
import com._Systems.calorietracker.DTOs.MealDTO;
import com._Systems.calorietracker.entity.Meal;
import com._Systems.calorietracker.entity.User;
import com._Systems.calorietracker.exception.ResourceNotFoundException;
import com._Systems.calorietracker.mapper.MealMapper;
import com._Systems.calorietracker.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {


    private final MealRepository mealRepository;
    private final UserService userService;
    private final DishService dishService;
    private final MealMapper mealMapper;

    public Meal createMeal(MealDTO dto) {
        Meal meal = mealMapper.toEntity(dto);
        if (meal.getDateTime() == null) {
            meal.setDateTime(LocalDateTime.now());
        }
        return mealRepository.save(meal);
    }

    public List<Meal> getMealsByUserAndDate(Long userId, LocalDateTime start, LocalDateTime end) {

        User user = userService.findById(userId);

        if (user == null)
        {
            throw new ResourceNotFoundException("Пользователя с id: " + userId + " не существует");
        }
        return mealRepository.findByUserIdAndDateTimeBetween(userId, start, end);
    }

    public MealDTO getMealDtoById(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Не найдена запись с id: " + id));
        return mealMapper.toDto(meal);
    }

    public DailyReportDTO getDailyReport(Long userId, LocalDate date) {

        User user = userService.findById(userId);

        if (user == null)
        {
            throw new ResourceNotFoundException("Пользователя с id: " + userId + " не существует");
        }

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        List<Meal> meals = getMealsByUserAndDate(userId, start, end);
        List<MealDTO> mealDtos = meals.stream()
                .map(mealMapper::toDto)
                .collect(Collectors.toList());

        double totalCalories = meals.stream()
                .flatMap(meal -> meal.getDishes().stream())
                .mapToDouble(dish -> dish.getCalories() != null ? dish.getCalories() : 0.0)
                .sum();

        return DailyReportDTO.builder()
                .date(date)
                .totalCalories(totalCalories)
                .meals(mealDtos)
                .build();
    }

    public CalorieNormCheckDTO checkCalorieNorm(Long userId, LocalDate date) {
        DailyReportDTO dailyReport = getDailyReport(userId, date);
        User user = userService.findById(userId);

        if (user == null)
        {
            throw new ResourceNotFoundException("Пользователя с id: " + userId + " не существует");
        }
        Double dailyNorm = user.getDailyCalorieNorm();

        return CalorieNormCheckDTO.builder()
                .date(date)
                .totalCalories(dailyReport.getTotalCalories())
                .dailyNorm(dailyNorm)
                .withinNorm(dailyReport.getTotalCalories() <= dailyNorm)
                .build();
    }


    public List<DailyReportDTO> getHistory(Long userId, LocalDate startDate, LocalDate endDate) {
        User user = userService.findById(userId);

        if (user == null)
        {
            throw new ResourceNotFoundException("Пользователя с id: " + userId + " не существует");
        }

        return startDate.datesUntil(endDate.plusDays(1))
                .map(date -> getDailyReport(userId, date))
                .collect(Collectors.toList());
    }


}