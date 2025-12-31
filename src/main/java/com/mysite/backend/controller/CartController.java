package com.mysite.backend.controller;

import com.mysite.backend.entity.Cart;
import com.mysite.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartRepository repository;

    @GetMapping
    public List<Cart> getList(@RequestParam LocalDate date) {
        // ★ 날짜 혹은 즐겨찾기 항목을 불러오도록 변경
        return repository.findAllByShoppingDateOrIsFavoriteTrue(date);
    }

    @PostMapping
    public Cart create(@RequestBody Cart item) {
        return repository.save(item);
    }

    @GetMapping("/search")
    public List<Cart> searchItems(@RequestParam String text) {
        // DB에서 해당 텍스트를 포함하는 모든 항목 조회
        return repository.findByTextContaining(text);
    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        return repository.findById(id).map(existingItem -> {
            existingItem.setIsBought(item.getIsBought());
            // ★ 즐겨찾기 상태와 쇼핑 날짜(삭제 시 null 처리 등) 반영을 위해 추가
            existingItem.setIsFavorite(item.getIsFavorite());
            existingItem.setShoppingDate(item.getShoppingDate());
            existingItem.setText(item.getText());
            return repository.save(existingItem);
        }).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}