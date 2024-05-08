package com.codecool.healnaturalisapp.controller;

import com.codecool.healnaturalisapp.dto.CategoryDTO;
import com.codecool.healnaturalisapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/all-main")
    public ResponseEntity<List<CategoryDTO>> getAllMainCategories() {
        return ResponseEntity.ok(categoryService.getAllMainCategories());
    }

    @GetMapping("/all-sub/{parentId}")
    public ResponseEntity<List<CategoryDTO>> getAllSubCategories(@PathVariable long parentId) {
        return ResponseEntity.ok(categoryService.getAllSubCategories(parentId));
    }

    @PostMapping("/add")
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.saveCategory(categoryDTO);
        return ResponseEntity.ok().build();
    }
}
