//package com._Systems.calorietracker.service;
//
//import com._Systems.calorietracker.DTOs.UserDTO;
//import com._Systems.calorietracker.ENUMs.Goal;
//import com._Systems.calorietracker.entity.User;
//import com._Systems.calorietracker.exception.ResourceNotFoundException;
//import com._Systems.calorietracker.mapper.UserMapper;
//import com._Systems.calorietracker.repository.UserRepository;
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
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private CalorieCalculatorService calorieCalculatorService;
//
//    @Mock
//    private UserMapper userMapper;
//
//    @InjectMocks
//    private UserService userService;
//
//    private UserDTO userDTO;
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        userDTO = UserDTO.builder()
//                .name("John")
//                .email("john@example.com")
//                .age(30)
//                .weight(70.0)
//                .height(170.0)
//                .goal(Goal.MAINTENANCE)
//                .build();
//
//        user = User.builder()
//                .id(1L)
//                .name("John")
//                .email("john@example.com")
//                .age(30)
//                .weight(70.0)
//                .height(170.0)
//                .goal(Goal.MAINTENANCE)
//                .dailyCalorieNorm(1647.87)
//                .build();
//    }
//
//    @Test
//    void testCreateUser() {
//        when(userMapper.toEntity(userDTO)).thenReturn(user);
//        when(calorieCalculatorService.calculateDailyNorm(user)).thenReturn(1647.87);
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        User createdUser = userService.createUser(userDTO);
//
//        assertNotNull(createdUser);
//        assertEquals(1647.87, createdUser.getDailyCalorieNorm(), 0.01);
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    void testFindById_Success() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
//
//        User foundUser = userService.findById(1L);
//
//        assertNotNull(foundUser);
//        assertEquals("John", foundUser.getName());
//    }
//
//    @Test
//    void testFindById_NotFound() {
//        when(userRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> userService.findById(1L));
//    }
//}