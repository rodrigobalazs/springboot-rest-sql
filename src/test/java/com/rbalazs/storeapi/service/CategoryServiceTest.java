package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.enums.AppConstants;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Junit associated to {@link CategoryService}. The Repository is mocked via Mockito Library.
 *
 * @author Rodrigo Balazs
 */
class CategoryServiceTest extends BaseServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Test
    void findByName() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category(AppConstants.CATEGORY_CLOTHING);
        when(categoryRepository.findByName(AppConstants.CATEGORY_CLOTHING)).thenReturn(Optional.of(category));

        // When
        Category retrievedCategory = categoryService.getCategoryByName(AppConstants.CATEGORY_CLOTHING);

        // Then
        verify(categoryRepository, atMostOnce()).findByName(AppConstants.CATEGORY_CLOTHING);

        assertAll(
                () -> assertNotNull(retrievedCategory),
                () -> assertEquals(AppConstants.CATEGORY_CLOTHING, retrievedCategory.getName())
		);

    }
}
