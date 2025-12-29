package com.mysite.backend.controller;

import com.mysite.backend.entity.Meal;
import com.mysite.backend.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
@CrossOrigin(origins = "*") // 모든 도메인에서 접속 허용 (테스트용)
public class MealController {
    private final MealRepository mealRepository;

    @GetMapping // 전체 식단 조회: 브라우저에서 http://localhost:8080/api/meals 접속 시 확인 가능
    public List<Meal> getList() {
        return mealRepository.findAll();
    }

    @PostMapping // 식단 저장: 리액트에서 데이터를 보낼 때 사용
    public Meal create(@RequestBody Meal meal) {
        return mealRepository.save(meal);
    }
    // MealController.java에 추가될 내용
    @DeleteMapping("/{id}") // 특정 ID의 식단을 삭제하는 API
    public void delete(@PathVariable Long id) {
        mealRepository.deleteById(id);
        // 삭제 후 리액트에서 목록을 다시 불러오면 화면에서도 사라져!
    }
}