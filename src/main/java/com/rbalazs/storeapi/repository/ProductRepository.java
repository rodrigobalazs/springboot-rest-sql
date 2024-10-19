package com.rbalazs.storeapi.repository;

import com.rbalazs.storeapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product Repository
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
