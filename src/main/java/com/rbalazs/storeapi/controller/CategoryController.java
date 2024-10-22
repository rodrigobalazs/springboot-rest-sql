package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.controller.swagger.CategoryControllerSwagger;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Category REST Controller.
 * API Documentation/Swagger at => https://<project_url>/swagger-ui/index.html
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerSwagger {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getCategories() {
        LOGGER.info("starts to execute categoryController.getCategories()");
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        LOGGER.info("starts to execute categoryController.getCategoryById() with ID:{}" , id);
        Category category = categoryService.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
        LOGGER.info("starts to execute categoryController.getCategoryByName() with name:{}" , name);
        Category category = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        LOGGER.info("starts to execute categoryController.save()");
        Category persistedCategory = categoryService.save(category);
        return new ResponseEntity<>(persistedCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("starts to execute categoryController.delete() with ID:{}" , id);
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        LOGGER.info("starts to execute categoryController.update() with ID:{}" , id);
        Category updatedCategory = categoryService.update(id, category);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
}

