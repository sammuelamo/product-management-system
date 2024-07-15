package com.product.productmanager.binarytree;

import com.product.productmanager.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryTreeService {

    private CategoryNode root;

    public CategoryTreeService() {
        this.root = null;
    }

    public void addCategory(Category category) {
        root = addRecursive(root, category);
    }

    private CategoryNode addRecursive(CategoryNode current, Category category) {
        if (current == null) {
            return new CategoryNode(category);
        }

        if (category.getName().compareTo(current.getCategory().getName()) < 0) {
            current.setLeft(addRecursive(current.getLeft(), category));
        } else if (category.getName().compareTo(current.getCategory().getName()) > 0) {
            current.setRight(addRecursive(current.getRight(), category));
        } else {
            return current; // Category already exists
        }

        return current;
    }
    public Category findCategory(String name) {
        return findRecursive(root, name);
    }

    private Category findRecursive(CategoryNode current, String name) {
        if (current == null) {
            return null;
        }

        if (name.equals(current.getCategory().getName())) {
            return current.getCategory();
        }

        return name.compareTo(current.getCategory().getName()) < 0
                ? findRecursive(current.getLeft(), name)
                : findRecursive(current.getRight(), name);
    }
}
