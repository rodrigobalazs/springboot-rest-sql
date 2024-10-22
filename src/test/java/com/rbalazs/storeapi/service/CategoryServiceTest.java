package com.rbalazs.storeapi.service;

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
        Category category = new Category("category1");
        when(categoryRepository.findByName("category1")).thenReturn(Optional.of(category));

        // When
        Category retrievedCategory = categoryService.getCategoryByName("category1");

        // Then
        verify(categoryRepository, atMostOnce()).findByName("category1");

        assertAll(
                () -> assertNotNull(retrievedCategory),
                () -> assertEquals("category1", retrievedCategory.getName())
		);

    }
}
