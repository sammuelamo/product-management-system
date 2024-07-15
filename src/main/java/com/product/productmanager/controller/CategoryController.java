package com.product.productmanager.controller;


import com.product.productmanager.entities.Category;
import com.product.productmanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

//    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

   @PostMapping("/add")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/all")
    public List<Category> getAllCategory() {
       return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable String id) {
       return categoryService.findCategoryById(id);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable String id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }
    @DeleteMapping("/delete/{id}")
    public Category deleteCategory(@PathVariable String id) {
       return categoryService.deleteCategory(id);
    }

}
