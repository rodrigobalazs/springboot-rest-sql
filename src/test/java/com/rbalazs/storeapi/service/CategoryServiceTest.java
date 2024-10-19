package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.convert.ConversionService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Junit associated to {@link CategoryService}. The Repository,Converter,etc are mocked via Mockito Library.
 *
 * @author Rodrigo Balazs
 */
class CategoryServiceTest extends BaseServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    ConversionService conversionService;

    @Test
    void findByName() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository, conversionService);
        Category category = new Category("category1");
        when(categoryRepository.findByName("category1")).thenReturn(category);

        CategoryDTO categoryDTO = populateCategoryDTO("category1");
        when(conversionService.convert(category, CategoryDTO.class)).thenReturn(categoryDTO);

        // When
        CategoryDTO retrievedCategoryDTO = categoryService.getCategoryByName("category1");

        // Then
        verify(categoryRepository, atMostOnce()).findByName("category1");
        verify(conversionService, atMostOnce()).convert(category, CategoryDTO.class);

        assertAll(
                () -> assertNotNull(retrievedCategoryDTO),
                () -> assertEquals("category1", retrievedCategoryDTO.getName())
		);

    }
}
