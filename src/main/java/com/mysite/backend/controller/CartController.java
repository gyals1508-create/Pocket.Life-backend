package com.mysite.backend.controller;

import com.mysite.backend.entity.Cart;
import com.mysite.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping") // 리액트 경로는 그대로 유지
@CrossOrigin(origins = "*")
public class CartController {
    private final CartRepository repository; // ★ 주입받는 레포지토리 변경

    @GetMapping
    public List<Cart> getList(@RequestParam LocalDate date) {
        return repository.findAllByShoppingDate(date);
    }

    @PostMapping
    public Cart create(@RequestBody Cart item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        item.setId(id);
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}