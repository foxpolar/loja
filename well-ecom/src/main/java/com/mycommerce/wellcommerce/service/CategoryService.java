package com.mycommerce.wellcommerce.service;


import com.mycommerce.wellcommerce.model.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategories();
    void createCategory(Category category);
    String deleteCategory(UUID categoryId);
    Category getCategory(UUID categoryId);
    Category updateCategory(Category category, UUID categoryId);
}
