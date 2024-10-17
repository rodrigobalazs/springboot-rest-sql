package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category REST Controller
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/category")
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
     * e.g Request => curl http://localhost:8080/category/getCategories
     */
    @GetMapping("/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        LOGGER.info("starts to execute categoryController.getCategories()");
        List<CategoryDTO> categoriesDTO = categoryService.getCategories();
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a Category by the ID given as parameter.
     *
     * @param id the category identifier to retrieve
     * @return a {@link CategoryDTO}
     *
     * e.g Request => curl http://localhost:8080/category/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoriesById(@PathVariable Long id) {
        LOGGER.info("starts to execute categoryController.getCategoriesById() with ID:{}" , id);
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    /**
     * Save a new Category into the repository.
     *
     * @param categoryDTO the categoryDTO to save
     * @return a {@link CategoryDTO} with the persisted category
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
        LOGGER.info("starts to execute categoryController.save() with ID:{}" , categoryDTO.getId());
        CategoryDTO persistedCategoryDTO = categoryService.save(categoryDTO);
        return new ResponseEntity<>(persistedCategoryDTO, HttpStatus.CREATED);
    }

    /**
     * Deletes a Category by the ID given as parameter.
     *
     * @param id the category identifier to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("starts to execute categoryController.delete() with ID:{}" , id);
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

