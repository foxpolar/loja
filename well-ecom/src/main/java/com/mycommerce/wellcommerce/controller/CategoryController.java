package com.mycommerce.wellcommerce.controller;

import com.mycommerce.wellcommerce.model.Category;
import com.mycommerce.wellcommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;

import static com.mycommerce.wellcommerce.constants.Constants.*;

@RestController
@RequestMapping(API_URL + API_VERSION)
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    private static final Logger logger = LogManager.getLogger(CategoryController.class);

    @GetMapping(PATH_CATEGORIES_PUBLIC)
    public ResponseEntity<List<Category>> getAllCategories() {
        logger.info("Starting searching for all categories");
        List<Category> allCategories = categoryService.getAllCategories();
        logger.info("Returning all categories");
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @PostMapping(PATH_CATEGORIES_PUBLIC)
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category) {
        logger.info("Starting creating a category");
        categoryService.createCategory(category);
        logger.info("Category added Successfully");
        return new ResponseEntity<>("Category added Successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping(PATH_CATEGORIES_PUBLIC + "/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable UUID categoryId) {
        try {
            logger.info("Starting deleting a category");
            String status = categoryService.deleteCategory(categoryId);
            logger.info("Category deleted Successfully");
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.info("Error deleting a category with reason: {} and status code: {}", e.getReason(), e.getStatusCode());
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @GetMapping(PATH_CATEGORIES_PUBLIC + "/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable UUID categoryId) {
        Category category = null;
        try {
            logger.info("Starting searching for one category");
            category = categoryService.getCategory(categoryId);
            logger.info("Category returned Successfully");
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.info("Error searching a category id: {}", categoryId);
            return new ResponseEntity<>(category, HttpStatus.NOT_FOUND );
        }
    }

    @PutMapping(PATH_CATEGORIES_PUBLIC + "/{categoryId}")
    public ResponseEntity<String> upDateCategory(@RequestBody Category category,
                                                 @PathVariable UUID categoryId) {
        try {
            logger.info("Starting update a category");
            categoryService.updateCategory(category, categoryId);
            logger.info("Category updated Successfully");
            return new ResponseEntity<>("Category with category id:  " + categoryId, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.info("Error updating a category id: {}", categoryId);
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
