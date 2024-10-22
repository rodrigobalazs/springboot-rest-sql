package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.model.Product;
import com.rbalazs.storeapi.repository.CategoryRepository;
import com.rbalazs.storeapi.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * Populates the database with some sample data with Categories and Products in case those tables are empty.
 */
@Service
public class PopulateSampleDataService implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(PopulateSampleDataService.class);

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        if ( CollectionUtils.isNotEmpty(categoryRepository.findAll())
                || CollectionUtils.isNotEmpty(productRepository.findAll())){
            return;
        }
        LOGGER.info("populates the database with some initial sample data for Categories and Products ..");
        createCategoryWithProduct("category1", "product1", 10.50);
        createCategoryWithProduct("category2", "product2", 4.4);
    }

    private void createCategoryWithProduct(String categoryName, String productName, Double productPrice){
        Category category = new Category(categoryName);
        categoryRepository.save(category);
        Product product = productRepository.save(new Product(productName,productPrice));
        category.addProducts(List.of(product));
        categoryRepository.save(category);
    }
}