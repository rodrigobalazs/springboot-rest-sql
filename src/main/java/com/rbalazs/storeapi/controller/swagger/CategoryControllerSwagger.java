package com.rbalazs.storeapi.controller.swagger;

import com.rbalazs.storeapi.controller.CategoryController;
import com.rbalazs.storeapi.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Documentation/Swagger interface related to {@link CategoryController}.
 * API Documentation/Swagger at => https://<project_url>/swagger-ui/index.html
 *
 * @author Rodrigo Balazs
 */
@Tag(name = "Category REST API", description = "API REST endpoints related to Categories")
public interface CategoryControllerSwagger {

    @Operation(summary = "Retrieves Categories",
            description = "Retrieves a list with all the available Categories" +
                    "e.g Request => curl http://localhost:8080/category/getCategories")
    public ResponseEntity<List<Category>> getCategories();

    @Operation(summary = "Retrieves a Category by ID",
            description = "Retrieves a Category by the Category ID given as parameter" +
                    "e.g Request => curl http://localhost:8080/category/1")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id);

    @Operation(summary = "Retrieves a Category by Name",
            description = "Retrieves a Category by the Category Name given as parameter")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name);

    @Operation(summary = "Saves a new Category", description = "Saves a new Category")
    public ResponseEntity<Category> save(@RequestBody Category category);

    @Operation(summary = "Deletes a Category by ID",
            description = "Deletes a Category by the Category ID given as parameter")
    public ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(summary = "Updates a Category by ID",
            description = "Updates the Category associated to the Category ID given as parameter")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category);
}
