package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.enums.AppValidations;
import com.rbalazs.storeapi.exception.CustomException;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Category Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    public Category save(Category category) {
        String categoryName = category.getName();
        if (categoryName == null || categoryName.isEmpty()){
            throw new CustomException(AppValidations.EMPTY_FIELDS);
        }

        if (getCategoryByName(categoryName) != null){
            throw new CustomException(AppValidations.ENTITY_NON_UNIQUE);
        }

        category.addProducts(category.getProducts());
        categoryRepository.save(category);
        return category;
    }

    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CustomException(AppValidations.ENTITY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }

    /**
     * Updates the Category associated to the Category ID given as parameter.
     *
     * @param id the category identifier
     * @param category a {@link Category} which contains the category´s attributes to update
     * @return a {@link Category} with the updated Category
     */
    public Category update(Long id, Category category) {
        if (!categoryRepository.existsById(id)) {
            throw new CustomException(AppValidations.ENTITY_NOT_FOUND);
        }
        category.setId(id);
        category.addProducts(category.getProducts());
        return categoryRepository.save(category);
    }
}
