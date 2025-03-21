//package com._Systems.calorietracker.service;
//
//import com._Systems.calorietracker.DTOs.DishDTO;
//import com._Systems.calorietracker.entity.Dish;
//import com._Systems.calorietracker.exception.ResourceNotFoundException;
//import com._Systems.calorietracker.mapper.DishMapper;
//import com._Systems.calorietracker.repository.DishRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class DishServiceTest {
//
//    @Mock
//    private DishRepository dishRepository;
//
//    @Mock
//    private DishMapper dishMapper;
//
//    @InjectMocks
//    private DishService dishService;
//
//    private DishDTO dishDTO;
//    private Dish dish;
//
//    @BeforeEach
//    void setUp() {
//        dishDTO = DishDTO.builder()
//                .name("Salad")
//                .calories(200.0)
//                .protein(10.0)
//                .fat(5.0)
//                .carbs(15.0)
//                .build();
//
//        dish = Dish.builder()
//                .id(1L)
//                .name("Salad")
//                .calories(200.0)
//                .protein(10.0)
//                .fat(5.0)
//                .carbs(15.0)
//                .build();
//    }
//
//    @Test
//    void testCreateDish() {
//        when(dishMapper.toEntity(dishDTO)).thenReturn(dish);
//        when(dishRepository.save(any(Dish.class))).thenReturn(dish);
//
//        Dish createdDish = dishService.createDish(dishDTO);
//
//        assertNotNull(createdDish);
//        assertEquals("Salad", createdDish.getName());
//        verify(dishRepository, times(1)).save(dish);
//    }
//
//    @Test
//    void testFindById_Success() {
//        when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));
//
//        Dish foundDish = dishService.findById(1L);
//
//        assertNotNull(foundDish);
//        assertEquals("Salad", foundDish.getName());
//    }
//
//    @Test
//    void testFindById_NotFound() {
//        when(dishRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> dishService.findById(1L));
//    }
//}