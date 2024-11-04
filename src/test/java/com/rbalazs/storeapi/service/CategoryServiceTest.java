package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.enums.AppConstants;
import com.rbalazs.storeapi.exception.CustomException;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.model.Product;
import com.rbalazs.storeapi.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * CategoryService Test. The Repository is mocked via Mockito.
 */
class CategoryServiceTest extends BaseServiceTest {

    final Long categoryClothingId = AppConstants.CATEGORY_CLOTHING_ID;
    final String categoryClothingName = AppConstants.CATEGORY_CLOTHING_NAME;

    @Mock
    CategoryRepository categoryRepository;

    @Test
    void getCategoryById() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category(categoryClothingName);
        category.setId(categoryClothingId);
        when(categoryRepository.findById(categoryClothingId)).thenReturn(Optional.of(category));

        // When
        Category retrievedCategory = categoryService.getCategoryById(categoryClothingId);

        // Then
        verify(categoryRepository, atMostOnce()).findById(categoryClothingId);

        assertAll(
                () -> assertNotNull(retrievedCategory),
                () -> assertEquals(categoryClothingId, retrievedCategory.getId())
        );
    }

    @Test
    void getCategoryByName() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category(categoryClothingName);
        when(categoryRepository.findByName(categoryClothingName)).thenReturn(Optional.of(category));

        // When
        Category retrievedCategory = categoryService.getCategoryByName(categoryClothingName);

        // Then
        verify(categoryRepository, atMostOnce()).findByName(categoryClothingName);

        assertAll(
                () -> assertNotNull(retrievedCategory),
                () -> assertEquals(categoryClothingName, retrievedCategory.getName())
		);
    }

    @Test
    void getCategories() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        when(categoryRepository.findAll()).thenReturn(List.of(new Category("category1"),
                new Category("category2")));

        // When
        List<Category> categories = categoryService.getCategories();

        // Then
        verify(categoryRepository, atMostOnce()).findAll();
        assertAll(
                () -> assertNotNull(categories),
                () -> assertEquals(2, categories.size())
        );
    }

    @Test
    void save() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Product product = new Product("product1", 10.2);
        Category category = new Category(categoryClothingName);
        category.setProducts(List.of(product));
        when(categoryRepository.save(category)).thenReturn(category);

        // When
        Category savedCategory = categoryService.save(category);

        // Then
        verify(categoryRepository, atMostOnce()).save(category);
        assertNotNull(savedCategory);
    }

    @Test
    void save_emptyCategoryName() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category();
        category.setName("");
        when(categoryRepository.save(category)).thenReturn(category);

        // When
        assertThrows(CustomException.class, () -> categoryService.save(category));

        // Then
        verify(categoryRepository, never()).save(category);
    }

    @Test
    void delete() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        Category category = new Category(categoryClothingName);
        when(categoryRepository.findById(categoryClothingId)).thenReturn(Optional.of(category));
        when(categoryRepository.existsById(categoryClothingId)).thenReturn(true);

        // When
        categoryService.delete(categoryClothingId);

        // Then
        verify(categoryRepository, atMostOnce()).findById(categoryClothingId);
        verify(categoryRepository, atMostOnce()).existsById(categoryClothingId);
    }

    @Test
    void delete_entityNotExist() {

        // Given
        CategoryService categoryService = new CategoryService(categoryRepository);
        when(categoryRepository.existsById(100L)).thenReturn(false);

        // When
        assertThrows(CustomException.class, () -> categoryService.delete(100L));

        // Then
        verify(categoryRepository, atMostOnce()).existsById(100L);
        verify(categoryRepository, never()).deleteById(100L);
    }
}
