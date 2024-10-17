package com.rbalazs.storeapi.controller;

import com.rbalazs.storeapi.dto.ProductDTO;
import com.rbalazs.storeapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Product REST Controller
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

    /**
     * Retrieves a list with all the Products
     *
     * @return a list of {@link ProductDTO}
     *
     * e.g Request => curl http://localhost:8080/product/getProducts
     */
    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        LOGGER.info("starts to execute productController.getProducts()");
        List<ProductDTO> productsDTO = productService.getProducts();
        return new ResponseEntity<>(productsDTO, HttpStatus.OK);
    }

    /**
     * Retrieves a Product by the ID given as parameter.
     *
     * @param id the product identifier to retrieve
     * @return a {@link ProductDTO}
     *
     * e.g Request => curl http://localhost:8080/product/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductsById(@PathVariable Long id) {
        LOGGER.info("starts to execute productController.getProductsById() with ID:{}" , id);
        ProductDTO productDTO = productService.getProductById(id);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}

