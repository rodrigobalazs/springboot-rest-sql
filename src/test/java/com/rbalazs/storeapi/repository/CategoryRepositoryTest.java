package com.rbalazs.storeapi.repository;

import com.rbalazs.storeapi.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Junit associated to {@link CategoryRepository}.
 *
 * @author Rodrigo Balazs
 */
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    public void beforeEach(){
        categoryRepository.save(new Category("category1"));
    }

    @AfterEach
    public void afterEach(){
        categoryRepository.deleteAll();
    }

    @Test
    void findByName() {
        Category category = categoryRepository.findByName("category1");
        assertAll(
                () -> assertNotNull(category),
                () -> assertEquals("category1", category.getName())
        );
    }
}