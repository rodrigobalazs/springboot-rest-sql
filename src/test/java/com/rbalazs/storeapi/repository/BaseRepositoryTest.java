package com.rbalazs.storeapi.repository;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

/**
 * Base Repository Test class with common behaviour
 *
 * @author Rodrigo Balazs
 */
public class BaseRepositoryTest {

    public static MySQLContainer container = new MySQLContainer<>("mysql:5.7.4")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("store_db")
            .withReuse(true);

    @BeforeAll
    public static void setUp() {
        container.start();
    }

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
