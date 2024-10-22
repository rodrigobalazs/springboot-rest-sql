package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.enums.AppConstants;
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
        Category furnitureCategory = createCategory(AppConstants.CATEGORY_FURNITURE);
        addProductToCategory(furnitureCategory, AppConstants.PRODUCT_SOFA, AppConstants.PRODUCT_SOFA_PRICE);
        addProductToCategory(furnitureCategory, AppConstants.PRODUCT_OFFICE_CHAIR,
                AppConstants.PRODUCT_OFFICE_CHAIR_PRICE);

        Category clothingCategory = createCategory(AppConstants.CATEGORY_CLOTHING);
        addProductToCategory(clothingCategory, AppConstants.PRODUCT_COAT, AppConstants.PRODUCT_COAT_PRICE);
        addProductToCategory(clothingCategory, AppConstants.PRODUCT_SWEATER, AppConstants.PRODUCT_SWEATER_PRICE);

    }

    private Category createCategory(String categoryName){
        Category category = new Category(categoryName);
        return categoryRepository.save(category);
    }

    private void addProductToCategory(Category category, String productName, Double productPrice){
        Product product = productRepository.save(new Product(productName,productPrice));
        category.addProducts(List.of(product));
        categoryRepository.save(category);
    }
}