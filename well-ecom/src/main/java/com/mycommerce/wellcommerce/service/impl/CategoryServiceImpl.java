package com.mycommerce.wellcommerce.service.impl;

import com.mycommerce.wellcommerce.model.Category;
import com.mycommerce.wellcommerce.repository.CategoryRepository;
import com.mycommerce.wellcommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import static com.mycommerce.wellcommerce.constants.Constants.RESOURCE_NOT_FOUND;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND));
        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully!!";
    }

    @Override
    public Category getCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,RESOURCE_NOT_FOUND));
    }

    @Override
    public Category updateCategory(Category category, UUID categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, RESOURCE_NOT_FOUND));
            return categoryRepository.save(category);
    }
}
