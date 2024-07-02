package com.arquitecture.ecommerce.infrastructure.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_father_id")
    private CategoryEntity parentCategory;
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
    private Set<CategoryEntity> subcategories;
    
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
    public CategoryEntity getParentCategory() {
        return parentCategory;
    }
    public void setParentCategory(CategoryEntity parentCategory) {
        this.parentCategory = parentCategory;
    }
    public Set<CategoryEntity> getSubcategories() {
        return subcategories;
    }
    public void setSubcategories(Set<CategoryEntity> subcategories) {
        this.subcategories = subcategories;
    }
    
}
