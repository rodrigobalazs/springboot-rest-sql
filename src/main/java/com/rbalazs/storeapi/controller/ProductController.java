package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.dto.ProductDTO;
import com.rbalazs.storeapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product REST Controller
 * API Documentation / Swagger at => https://<project_url>/swagger-ui/index.html
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    @Operation(summary = "Retrieves all Products",
            description = "Retrieves a list with all the availables Products | e.g Request => curl http://localhost:8080/product/getProducts")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        LOGGER.info("starts to execute productController.getProducts()");
        List<ProductDTO> productsDTO = productService.getProducts();
        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieves a Product by ID",
            description = "Retrieves a Product by the Product ID given as parameter | e.g Request => curl http://localhost:8080/product/1")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        LOGGER.info("starts to execute productController.getProductById() with ID:{}" , id);
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Retrieves a Product by Name",
            description = "Retrieves a Product by the Product Name given as parameter")
    public ResponseEntity<ProductDTO> getProductByName(@PathVariable String name) {
        LOGGER.info("starts to execute productController.getProductByName() with name:{}" , name);
        ProductDTO productDTO = productService.getProductByName(name);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}

