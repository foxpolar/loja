package com.mycommerce.wellcommerce.controller;

import com.mycommerce.wellcommerce.model.Category;
import com.mycommerce.wellcommerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.mycommerce.wellcommerce.constants.Constants.*;

@RestController
@RequestMapping(PATH + VERSION)
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
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        logger.info("Starting creating a category");
        categoryService.createCategory(category);
        logger.info("Category added Successfully");
        return new ResponseEntity<>("Category added Successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping(PATH_CATEGORIES_PUBLIC + "/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
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
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
        Category category = null;
        try {
            logger.info("Starting searching for one category");
            category = categoryService.getCategory(categoryId);
            logger.info("Category returned Successfully");
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.info("Error searching a category id: {}", categoryId);
            return new ResponseEntity<>(category, category == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
        }
    }

    @PutMapping(PATH_CATEGORIES_PUBLIC + "/{categoryId}")
    public ResponseEntity<String> upDateCategory(@RequestBody Category category,
                                                 @PathVariable Long categoryId) {
        try {
            logger.info("Starting update a category");
            Category savedcategory = categoryService.updateCategory(category, categoryId);
            logger.info("Category updated Successfully");
            return new ResponseEntity<>("Category with category id:  " + categoryId, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            logger.info("Error updating a category id: {}", categoryId);
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
