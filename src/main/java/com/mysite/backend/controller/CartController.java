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
        // 특정 날짜 항목과 즐겨찾기 항목을 안전하게 결합하여 반환
        return repository.findAllByShoppingDateOrIsFavoriteTrue(date);
    }

    @PostMapping
    public Cart create(@RequestBody Cart item) {
        return repository.save(item);
    }

    @GetMapping("/search")
    public List<Cart> searchItems(@RequestParam String text) {
        // 검색 시 텍스트 매칭과 더불어 날짜가 있는(실제 구매 이력이 있는) 데이터 위주로 필터링 권장
        return repository.findByTextContaining(text);
    }

    @PutMapping("/{id}")
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        return repository.findById(id).map(existingItem -> {
            existingItem.setIsBought(item.getIsBought());
            existingItem.setIsFavorite(item.getIsFavorite());
            existingItem.setShoppingDate(item.getShoppingDate());
            existingItem.setText(item.getText());
            existingItem.setCount(item.getCount());
            return repository.save(existingItem);
        }).orElseThrow(() -> new RuntimeException("항목을 찾을 수 없습니다."));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // 엔티티 삭제 (프론트에서 즐겨찾기 여부에 따라 PUT으로 날짜만 지울지, 실제 DELETE할지 결정함)
        repository.deleteById(id);
    }
}