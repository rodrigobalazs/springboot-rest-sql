package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.exception.EntityNotFoundException;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Category Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /** Spring DTO/Model Conversion Service. */
    private final ConversionService conversionService;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ConversionService conversionService) {
        this.categoryRepository = categoryRepository;
        this.conversionService = conversionService;
    }

    /**
     * Retrieves a list with all the Categories
     *
     * @return a list of {@link CategoryDTO}
     */
    public List<CategoryDTO> getCategories() {
        return conversionService.convert(categoryRepository.findAll(), List.class);
    }

    /**
     * Retrieves a Category by the Category ID given as parameter.
     *
     * @param id the category identifier to retrieve
     * @return a {@link CategoryDTO}
     */
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        return conversionService.convert(optionalCategory.get(), CategoryDTO.class);
    }

    /**
     * Retrieves a Category by the Category Name given as parameter.
     *
     * @param name the category name to retrieve
     * @return a {@link CategoryDTO}
     */
    public CategoryDTO getCategoryByName(String name) {
        Optional<Category> optionalCategory = Optional.ofNullable(categoryRepository.findByName(name));
        if (optionalCategory.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return conversionService.convert(optionalCategory.get(), CategoryDTO.class);
    }

    /**
     * Save a new Category into the repository.
     *
     * @param categoryDTO the categoryDTO to save
     * @return a {@link CategoryDTO} with the persisted category
     */
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = conversionService.convert(categoryDTO, Category.class);
        Category persistedCategory = categoryRepository.save(Objects.requireNonNull(category));
        return conversionService.convert(persistedCategory, CategoryDTO.class);
    }

    /**
     * Deletes a Category by the ID given as parameter.
     *
     * @param id the category identifier to delete
     */
    public void delete(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException(id);
        }
        categoryRepository.deleteById(id);
    }

    /**
     * Updates the Category associated to the Category ID given as parameter.
     *
     * @param id the category identifier
     * @param categoryDTO the categoryDTO with the category attributes to update
     * @return a {@link CategoryDTO} with the updated Category
     */
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new EntityNotFoundException(id);
        }

        Category categoryToUpdate = conversionService.convert(categoryDTO, Category.class);
        Category updatedCategory = categoryRepository.save(Objects.requireNonNull(categoryToUpdate));
        return conversionService.convert(updatedCategory, CategoryDTO.class);
    }
}
