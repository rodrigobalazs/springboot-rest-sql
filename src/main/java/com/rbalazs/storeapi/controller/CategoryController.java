package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

/**
 * Category REST Controller | API Documentation/Swagger at => https://<project_url>/swagger-ui/index.html
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

    @GetMapping("/getCategories")
    @Operation(summary = "Retrieves Categories",
            description = "Retrieves a list with all the available Categories" +
                          "e.g Request => curl http://localhost:8080/category/getCategories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        LOGGER.info("starts to execute categoryController.getCategories()");
        List<CategoryDTO> categoriesDTO = categoryService.getCategories();
        return new ResponseEntity<>(categoriesDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a Category by ID",
            description = "Retrieves a Category by the Category ID given as parameter" +
                          "e.g Request => curl http://localhost:8080/category/1")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        LOGGER.info("starts to execute categoryController.getCategoryById() with ID:{}" , id);
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Retrieves a Category by Name",
            description = "Retrieves a Category by the Category Name given as parameter")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name) {
        LOGGER.info("starts to execute categoryController.getCategoryByName() with name:{}" , name);
        CategoryDTO categoryDTO = categoryService.getCategoryByName(name);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Saves a new Category", description = "Saves a new Category")
    public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
        LOGGER.info("starts to execute categoryController.save() with ID:{}" , categoryDTO.getId());
        CategoryDTO persistedCategoryDTO = categoryService.save(categoryDTO);
        return new ResponseEntity<>(persistedCategoryDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Category by ID",
            description = "Deletes a Category by the Category ID given as parameter")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("starts to execute categoryController.delete() with ID:{}" , id);
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Category by ID",
            description = "Updates the Category associated to the Category ID given as parameter")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        LOGGER.info("starts to execute categoryController.update() with ID:{}" , id);
        CategoryDTO updatedCategoryDTO = categoryService.update(id, categoryDTO);
        return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
    }
}

