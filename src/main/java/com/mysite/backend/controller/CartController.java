package com.mysite.backend.controller;

import com.mysite.backend.entity.Cart;
import com.mysite.backend.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopping")
@CrossOrigin(origins = "*")
public class CartController {
    private final CartRepository repository;

    @GetMapping
    public List<Cart> getList(@RequestParam LocalDate date) {
        // 1. 해당 날짜 데이터와 즐겨찾기 데이터를 가져옴
        List<Cart> list = repository.findAllByShoppingDateOrIsFavoriteTrue(date);

        // 2. 핵심 로직: 반환하기 전, 동일한 이름을 가진 품목 중 즐겨찾기 된 게 있다면 상태 동기화
        return list.stream().map(item -> {
            if (repository.existsByTextAndIsFavoriteTrue(item.getText())) {
                item.setIsFavorite(true);
            }
            return item;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public Cart create(@RequestBody Cart item) {
        // 추가 시에도 동일 이름의 즐겨찾기가 있다면 적용해서 저장
        if (repository.existsByTextAndIsFavoriteTrue(item.getText())) {
            item.setIsFavorite(true);
        }
        return repository.save(item);
    }

    @GetMapping("/search")
    public List<Cart> searchItems(@RequestParam String text) {
        return repository.findByTextContaining(text);
    }

    @PutMapping("/{id}")
    @Transactional
    public Cart update(@PathVariable Long id, @RequestBody Cart item) {
        Cart target = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("항목을 찾을 수 없습니다."));

        // 즐겨찾기 상태가 변경된 경우, DB 내의 모든 동일 명칭 품목 상태를 강제 동기화
        if (target.getIsFavorite() != item.getIsFavorite()) {
            List<Cart> allRelated = repository.findAllByText(target.getText());
            for (Cart c : allRelated) {
                c.setIsFavorite(item.getIsFavorite());
            }
        }

        target.setIsBought(item.getIsBought());
        target.setShoppingDate(item.getShoppingDate());
        target.setText(item.getText());
        target.setCount(item.getCount());
        target.setIsFavorite(item.getIsFavorite());

        return repository.save(target);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}