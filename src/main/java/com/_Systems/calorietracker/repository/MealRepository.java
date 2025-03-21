package com._Systems.calorietracker.repository;

import com._Systems.calorietracker.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByUserIdAndDateTimeBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
