package com.product.productmanager.binarytree;

import com.product.productmanager.entities.Category;

public class CategoryNode {
    private Category category;
    private CategoryNode left;
    private CategoryNode right;

    public CategoryNode(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryNode getLeft() {
        return left;
    }

    public void setLeft(CategoryNode left) {
        this.left = left;
    }

    public CategoryNode getRight() {
        return right;
    }

    public void setRight(CategoryNode right) {
        this.right = right;
    }
}

