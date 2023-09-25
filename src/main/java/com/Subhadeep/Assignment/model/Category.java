package com.Subhadeep.Assignment.model;

import javax.persistence.Embeddable;

@Embeddable
public class Category {
    private Integer CategoryId;
    private String CategoryName;

    public Category() {
    }

    public Category(Integer categoryId, String categoryName) {
        CategoryId = categoryId;
        CategoryName = categoryName;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

}

