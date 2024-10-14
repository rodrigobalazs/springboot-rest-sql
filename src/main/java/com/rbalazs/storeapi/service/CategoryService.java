package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.exception.EntityNotFoundException;
import com.rbalazs.storeapi.model.Category;
import com.rbalazs.storeapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Category Service
 *
 * @author Rodrigo Balazs
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /** Spring Conversion Service, used to convert between DTO / Model. */
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
     * Retrieves a Category by the ID given as parameter.
     *
     * @param id the category ID to retrieve
     * @return a {@link CategoryDTO}
     */
    public CategoryDTO getCategoryById(Long id) {
        Optional<Category> result = categoryRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        return conversionService.convert(result.get(), CategoryDTO.class);
    }
}
