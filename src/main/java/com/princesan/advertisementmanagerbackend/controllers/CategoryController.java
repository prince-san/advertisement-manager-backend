package com.princesan.advertisementmanagerbackend.controllers;

import com.princesan.advertisementmanagerbackend.model.dtos.CategoryDto;
import com.princesan.advertisementmanagerbackend.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/")
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDto get(@PathVariable String id) {
        CategoryDto categoryDto = categoryService.get(Long.parseLong(id));
        System.out.println(categoryDto.getName());
        return categoryDto;
    }
}
