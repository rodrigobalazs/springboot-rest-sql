package com.rbalazs.storeapi.repository;

import com.rbalazs.storeapi.enums.AppConstants;
import com.rbalazs.storeapi.model.Category;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryRepositoryTest extends BaseRepositoryTest {

    final String categoryFurnitureName = AppConstants.CATEGORY_FURNITURE_NAME;
    final Long categoryFurnitureId = AppConstants.CATEGORY_FURNITURE_ID;
    final String categorySportsName = AppConstants.CATEGORY_SPORTS_NAME;

    @Test
    void findByName() {
        Optional<Category> category = categoryRepository.findByName(categoryFurnitureName);
        assertAll(
                () -> assertNotNull(category.get()),
                () -> assertEquals(categoryFurnitureName, category.get().getName())
        );
    }

    @Test
    void findById() {
        Optional<Category> category = categoryRepository.findById(categoryFurnitureId);
        assertAll(
                () -> assertNotNull(category.get()),
                () -> assertEquals(categoryFurnitureId, category.get().getId()),
                () -> assertEquals(categoryFurnitureName, category.get().getName())
        );
    }

    @Test
    void save() {
        // Given
        Category category = new Category(categorySportsName);
        categoryRepository.save(category);

        // When
        Optional<Category> retrievedCategory = categoryRepository.findByName(categorySportsName);

        // Then
        assertAll(
                () -> assertNotNull(retrievedCategory.get()),
                () -> assertEquals(categorySportsName, retrievedCategory.get().getName())
        );
    }
}