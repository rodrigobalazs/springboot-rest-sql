package com.rbalazs.storeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Categories REST Controller
 *
 * @author Rodrigo Balazs
 */
@RestController
public class CategoryController {

    @GetMapping("/getCategories")
    public String ping() {
        return "Category1";
    }
}