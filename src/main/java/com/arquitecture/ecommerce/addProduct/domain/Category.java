package com.arquitecture.ecommerce.addProduct.domain;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.Set;

@Entity
@Table(name="Category")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String nameCategory;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    public Category() {}


    public Category(Long categoryId, String nameCategory, Set<Product> products) {
        this.categoryId = categoryId;
        this.nameCategory = nameCategory;
        this.products = products;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
