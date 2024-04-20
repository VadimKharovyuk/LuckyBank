//package com.example.luckybank.service;
//
//import com.example.luckybank.model.Food;
//import com.example.luckybank.repositoty.FoodRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@AllArgsConstructor
//public class FoodService {
//    private final FoodRepository foodRepository ;
//
//    public List<Food> getAllFood() {
//      return   foodRepository.findAll();
//    }
//
//    public Food getFoodById(Long foodId) {
//        return foodRepository.findById(foodId)
//                .orElseThrow(() -> new EntityNotFoundException("Food with id " + foodId + " not found"));
//    }
//
//}
