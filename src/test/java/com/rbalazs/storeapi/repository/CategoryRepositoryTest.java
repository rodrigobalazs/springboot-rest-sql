package com.rbalazs.storeapi.repository;

import com.rbalazs.storeapi.enums.AppConstants;
import com.rbalazs.storeapi.model.Category;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit associated to {@link CategoryRepository}.
 *
 * @author Rodrigo Balazs
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryRepositoryTest extends BaseRepositoryTest {

    @Test
    void findByName() {
        Optional<Category> category = categoryRepository.findByName(AppConstants.CATEGORY_FURNITURE);
        assertAll(
                () -> assertNotNull(category.get()),
                () -> assertEquals(AppConstants.CATEGORY_FURNITURE, category.get().getName())
        );
    }

    @Test
    void findById() {
        Optional<Category> category = categoryRepository.findById(AppConstants.CATEGORY_FURNITURE_ID);
        assertAll(
                () -> assertNotNull(category.get()),
                () -> assertEquals(AppConstants.CATEGORY_FURNITURE_ID, category.get().getId()),
                () -> assertEquals(AppConstants.CATEGORY_FURNITURE, category.get().getName())
        );
    }
}