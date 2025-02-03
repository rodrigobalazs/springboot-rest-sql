package com.rbalazs.storeapi.repository;

import com.rbalazs.storeapi.service.PopulateSampleDataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/**
 * Base Integration Test class for Repository layer with common methods/attributes.
 * The Testing Database it´s dockerized via TestContainer.
 *
 * @author Rodrigo Balazs
 */
public class BaseRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PopulateSampleDataService populateSampleDataService;

    /* For an unknown reason the Mysql Docker container applying an image equal/greater than 6.0.0 fails
        to bootstrap properly during 'mvn test' */
    public static MySQLContainer container = new MySQLContainer<>("mysql:5.7.4")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("store_db")
            .withReuse(true);

    @BeforeAll
    public static void setUp() {
        container.start();
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        populateSampleDataService.run();
    }

    @AfterEach
    public void afterEach(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    /**
     * Overrides some properties at application.properties with some specific values from the dockerized
     * Testing Database.
     */
    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
