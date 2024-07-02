package com.arquitecture.ecommerce.domain.models;

import java.util.Set;

public class Category {

    private Long id;
    private String name;
    private Category parentCategory;
    private Set<Category> subcategories;
    
    public Category() {}

    public Category(Long id, String name, Category parentCategory, Set<Category> subcategories) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.subcategories = subcategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }
    
}
