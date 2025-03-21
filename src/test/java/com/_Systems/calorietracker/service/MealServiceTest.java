//package com._Systems.calorietracker.service;
//
//import com._Systems.calorietracker.DTOs.CalorieNormCheckDTO;
//import com._Systems.calorietracker.DTOs.DailyReportDTO;
//import com._Systems.calorietracker.DTOs.MealDTO;
//import com._Systems.calorietracker.entity.Dish;
//import com._Systems.calorietracker.entity.Meal;
//import com._Systems.calorietracker.entity.User;
//import com._Systems.calorietracker.exception.ResourceNotFoundException;
//import com._Systems.calorietracker.mapper.MealMapper;
//import com._Systems.calorietracker.repository.MealRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class MealServiceTest {
//
//    @Mock
//    private MealRepository mealRepository;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private DishService dishService;
//
//    @Mock
//    private MealMapper mealMapper;
//
//    @InjectMocks
//    private MealService mealService;
//
//    private MealDTO mealDTO;
//    private Meal meal;
//    private User user;
//    private Dish dish;
//
//    @BeforeEach
//    void setUp() {
//        user = User.builder().id(1L).dailyCalorieNorm(1647.87).build();
//        dish = Dish.builder().id(1L).calories(200.0).build();
//
//        mealDTO = MealDTO.builder()
//                .userId(1L)
//                .dishIds(Arrays.asList(1L))
//                .dateTime(LocalDateTime.now())
//                .build();
//
//        meal = Meal.builder()
//                .id(1L)
//                .user(user)
//                .dishes(Arrays.asList(dish))
//                .dateTime(LocalDateTime.now())
//                .build();
//    }
//
//    @Test
//    void testCreateMeal() {
//        when(mealMapper.toEntity(mealDTO)).thenReturn(meal);
//        when(userService.findById(1L)).thenReturn(user);
//        when(dishService.findById(1L)).thenReturn(dish);
//        when(mealRepository.save(any(Meal.class))).thenReturn(meal);
//
//        Meal createdMeal = mealService.createMeal(mealDTO);
//
//        assertNotNull(createdMeal);
//        assertEquals(1, createdMeal.getDishes().size());
//        verify(mealRepository, times(1)).save(meal);
//    }
//
//    @Test
//    void testGetDailyReport() {
//        LocalDate date = LocalDate.now();
//        LocalDateTime start = date.atStartOfDay();
//        LocalDateTime end = date.atTime(23, 59, 59);
//
//        when(mealRepository.findByUserIdAndDateTimeBetween(1L, start, end)).thenReturn(Arrays.asList(meal));
//        when(mealMapper.toDto(meal)).thenReturn(mealDTO);
//
//        DailyReportDTO report = mealService.getDailyReport(1L, date);
//
//        assertNotNull(report);
//        assertEquals(200.0, report.getTotalCalories(), 0.01);
//        assertEquals(1, report.getMeals().size());
//    }
//
//    @Test
//    void testCheckCalorieNorm() {
//        LocalDate date = LocalDate.now();
//        when(mealRepository.findByUserIdAndDateTimeBetween(eq(1L), any(), any())).thenReturn(Arrays.asList(meal));
//        when(mealMapper.toDto(meal)).thenReturn(mealDTO);
//        when(userService.findById(1L)).thenReturn(user);
//
//        CalorieNormCheckDTO check = mealService.checkCalorieNorm(1L, date);
//
//        assertNotNull(check);
//        assertEquals(200.0, check.getTotalCalories(), 0.01);
//        assertTrue(check.getWithinNorm());
//    }
//
//    @Test
//    void testGetMealDtoById_NotFound() {
//        when(mealRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> mealService.getMealDtoById(1L));
//    }
//}