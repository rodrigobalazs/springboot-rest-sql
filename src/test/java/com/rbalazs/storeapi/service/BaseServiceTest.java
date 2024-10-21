package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.dto.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

/**
 * Base Service Test class with common behaviour
 *
 * @author Rodrigo Balazs
 */
public class BaseServiceTest {

    /**
     * MockitoAnnotations.openMocks it´s required to be executed before each test to inject properly
     * each @Mock annotation.
     */
    @BeforeEach
    void initializeMocks(){
        MockitoAnnotations.openMocks(this);
    }

    CategoryDTO populateCategoryDTO(String name){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(name);
        return categoryDTO;
    }
}
