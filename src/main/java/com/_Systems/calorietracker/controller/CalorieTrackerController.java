package com._Systems.calorietracker.controller;

import com._Systems.calorietracker.DTOs.*;
import com._Systems.calorietracker.entity.Dish;
import com._Systems.calorietracker.entity.Meal;
import com._Systems.calorietracker.entity.User;
import com._Systems.calorietracker.service.DishService;
import com._Systems.calorietracker.service.MealService;
import com._Systems.calorietracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Calorie Tracker API", description = "API для отслеживания калорий и питания пользователей")
public class CalorieTrackerController {


    private final UserService userService;
    private final DishService dishService;
    private final MealService mealService;

    @PostMapping("/users")
    @Operation(summary = "Создать нового пользователя", description = "Добавляет нового пользователя и рассчитывает его дневную норму калорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content)
    })
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO dto) {
        User user = userService.createUser(dto);
        UserDTO response = userService.getUserDtoById(user.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/dishes")
    @Operation(summary = "Создать новое блюдо", description = "Добавляет новое блюдо с указанными калориями и макронутриентами")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Блюдо успешно создано",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DishDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content)
    })
    public ResponseEntity<DishDTO> createDish(@Valid @RequestBody DishDTO dto) {
        Dish dish = dishService.createDish(dto);
        DishDTO response = dishService.getDishDtoById(dish.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/meals")
    @Operation(summary = "Добавить прием пищи", description = "Создает новый прием пищи для пользователя с указанным списком блюд")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Прием пищи успешно добавлен",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MealDTO.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные входные данные", content = @Content)
    })
    public ResponseEntity<MealDTO> createMeal(@Valid @RequestBody MealDTO dto) {
        Meal meal = mealService.createMeal(dto);
        MealDTO response = mealService.getMealDtoById(meal.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reports/daily/{userId}")
    @Operation(summary = "Получить отчет за день", description = "Возвращает сумму калорий и список приемов пищи за указанный день")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Отчет успешно сформирован",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyReportDTO.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    })
    public ResponseEntity<DailyReportDTO> getDailyReport(
            @Parameter(description = "ID пользователя") @PathVariable Long userId,
            @Parameter(description = "Дата отчета в формате YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DailyReportDTO report = mealService.getDailyReport(userId, date);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/reports/check-norm/{userId}")
    @Operation(summary = "Проверить норму калорий", description = "Проверяет, уложился ли пользователь в дневную норму калорий")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Проверка выполнена",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CalorieNormCheckDTO.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    })
    public ResponseEntity<CalorieNormCheckDTO> checkCalorieNorm(
            @Parameter(description = "ID пользователя") @PathVariable Long userId,
            @Parameter(description = "Дата проверки в формате YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        CalorieNormCheckDTO check = mealService.checkCalorieNorm(userId, date);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/reports/history/{userId}")
    @Operation(summary = "Получить историю питания", description = "Возвращает отчеты по дням за указанный период")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "История успешно сформирована",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DailyReportDTO.class))),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content)
    })
    public ResponseEntity<List<DailyReportDTO>> getHistory(
            @Parameter(description = "ID пользователя") @PathVariable Long userId,
            @Parameter(description = "Начальная дата периода в формате YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "Конечная дата периода в формате YYYY-MM-DD")
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<DailyReportDTO> history = mealService.getHistory(userId, startDate, endDate);
        return ResponseEntity.ok(history);
    }
}