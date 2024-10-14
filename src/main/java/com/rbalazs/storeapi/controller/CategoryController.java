package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Category REST Controller
 *
 * @author Rodrigo Balazs
 */
@RestController
public class CategoryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Retrieves a list with all the Categories
     *
     * @return a list of {@link CategoryDTO}
     *
     * e.g Request => curl http://localhost:8080/getCategories
     */
    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        LOGGER.info("starts to execute CategoryController.getCategories()");
        List<CategoryDTO> response = categoryService.getCategories();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Retrieves a Category by the ID given as parameter.
     *
     * @param id the category ID to retrieve
     * @return a {@link CategoryDTO}
     *
     * e.g Request => curl http://localhost:8080/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoriesById(@PathVariable Long id) {
        LOGGER.info("starts to execute CategoryController.getCategoriesById() with ID:{}" , id);
        CategoryDTO response = categoryService.getCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

