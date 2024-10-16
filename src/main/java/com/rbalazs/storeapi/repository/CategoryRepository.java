package com.rbalazs.storeapi.repository;

import com.rbalazs.storeapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CRUD Category Repository
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {}
