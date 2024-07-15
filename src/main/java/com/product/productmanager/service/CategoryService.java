package com.product.productmanager.service;

import com.product.productmanager.binarytree.CategoryTreeService;
import com.product.productmanager.entities.Category;
import com.product.productmanager.exceptions.CategoryAlreadyExistsException;
import com.product.productmanager.exceptions.CategoryNotFoundException;
import com.product.productmanager.repository.CategoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryTreeService categoryTreeService;

//    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryTreeService categoryTreeService) {
        this.categoryRepository = categoryRepository;
        this.categoryTreeService = categoryTreeService;
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
    }

    public Category saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException(category.getName());
        }
        categoryTreeService.addCategory(category);
        return categoryRepository.save(category);
    }

    public Category deleteCategory(String id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
        return category;
    }

    public Category updateCategory(String id, Category category) {
        Category categoryFound = categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException(id));
        categoryFound.setName(category.getName());
        return categoryRepository.save(categoryFound);
    }
}
