package com.mysite.backend.controller;

import com.mysite.backend.entity.Meal;
import com.mysite.backend.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
@CrossOrigin(origins = "*")
public class MealController {
    private final MealRepository mealRepository;

    @GetMapping
    // 리액트에서 ?date=2025-12-30 이렇게 보내면 그걸 받아서 조회함
    public List<Meal> getList(@RequestParam LocalDate date) {
        return mealRepository.findAllByMealDate(date);
    }

    @PostMapping
    public Meal create(@RequestBody Meal meal) {
        return mealRepository.save(meal);
    }

    @PutMapping("/{id}")
    public Meal update(@PathVariable Long id, @RequestBody Meal mealDetails) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 기록을 찾을 수 없습니다."));

        meal.setText(mealDetails.getText()); // 새로운 내용으로 교체
        // 만약 식단 타입(아침/점심 등)도 수정 가능하게 하려면 아래 줄 추가
        // meal.setMealType(mealDetails.getMealType());

        return mealRepository.save(meal); // DB에 반영
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mealRepository.deleteById(id);
    }
}