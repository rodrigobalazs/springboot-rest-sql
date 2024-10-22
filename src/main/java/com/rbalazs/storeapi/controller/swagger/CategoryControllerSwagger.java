package com.rbalazs.storeapi.controller.swagger;

import com.rbalazs.storeapi.controller.CategoryController;
import com.rbalazs.storeapi.model.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Documentation/Swagger interface related to {@link CategoryController}.
 * API Documentation/Swagger at => https://<project_url>/swagger-ui/index.html
 *
 * @author Rodrigo Balazs
 */
@Tag(name = "Category API", description = "API endpoints related to Categories and associated Products")
public interface CategoryControllerSwagger {

    @Operation(summary = "Retrieves all Categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Array with all the Categories",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)))})})
    public ResponseEntity<List<Category>> getCategories();


    @Operation(summary = "Retrieves a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Object with the Category information",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Category.class))})})
    public ResponseEntity<Category> getCategoryById(
            @Parameter(description = "Category Identifier", example = "1", required = true) @PathVariable Long id);


    @Operation(summary = "Retrieves a Category by Name",
            description = "Retrieves a Category by the Category Name given as parameter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Object with the Category information",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Category.class))})})
    public ResponseEntity<Category> getCategoryByName(
            @Parameter(description = "Category Name", example = "category1", required = true) @PathVariable String name);


    @Operation(summary = "Saves a new Category")
    public ResponseEntity<Category> save(@RequestBody Category category);


    @Operation(summary = "Deletes a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns void / nothing",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Void.class)))})})
    public ResponseEntity<Void> delete(
            @Parameter(description = "Category Identifier to delete", example = "1", required = true) @PathVariable Long id);


    @Operation(summary = "Updates a Category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "returns a JSON Array with the updated Category",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = Category.class)))})})
    public ResponseEntity<Category> update(
            @Parameter(description = "Category Identifier to update", example = "1", required = true) @PathVariable Long id,
            @RequestBody Category category);
}
