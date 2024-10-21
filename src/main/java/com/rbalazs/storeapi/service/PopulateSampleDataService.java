package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.model.Product;
import com.rbalazs.storeapi.repository.CategoryRepository;
import com.rbalazs.storeapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Populates the database with some sample data with Categories and Products in case those tables are empty.
 */
@Service
public class PopulateSampleDataService implements CommandLineRunner {

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

        Category category1 = categoryRepository.save(new Category("category1"));
        Category category2 = categoryRepository.save(new Category("category2"));

        productRepository.save(new Product("product1",10.50,category1));
        productRepository.save(new Product("product2",4.4,category2));
    }
}