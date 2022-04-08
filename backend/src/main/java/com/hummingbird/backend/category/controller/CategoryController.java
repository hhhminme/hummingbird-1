package com.hummingbird.backend.category.controller;

import com.hummingbird.backend.category.dto.CreateCategoryDto;
import com.hummingbird.backend.category.dto.GetCategoryDto;
import com.hummingbird.backend.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
public class CategoryController {
    private CategoryService categoryService;


    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    //create
    @PostMapping("/category/new")
    public Long createCategory(@RequestBody HashMap<String, Object> data){
        CreateCategoryDto dto = CreateCategoryDto.builder()
                .name((String) data.get("categoryName"))
                .build();
        return categoryService.create(dto,Long.parseLong((String) data.get("menuId")));
    }

    //read
    @GetMapping("/category/get")
    public GetCategoryDto getCategory(Long categoryId){
        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/category/get/menu")
    public List<GetCategoryDto> getCategoryByMenu(Long menuId){
        return categoryService.getCategoryListByMenu(menuId);
    }

    //update
    @PostMapping("/category/update")
    public Long updateCategory(@RequestBody HashMap<String, Object> data){
        return categoryService.update(new Long((int)data.get("categoryId")), (String)data.get("categoryName"));
    }

    //delete
    @PostMapping("/category/delete")
    public boolean deleteCategory(Long categoryId) {
        return categoryService.delete(categoryId);
    }






}
