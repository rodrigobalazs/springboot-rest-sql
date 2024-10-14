package com.rbalazs.storeapi.mapper;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.model.Category;
import org.springframework.core.convert.converter.Converter;
import org.mapstruct.Mapper;

/**
 * Converts a given {@link Category} Model into a {@link CategoryDTO} via Spring Converter
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends Converter<Category, CategoryDTO> {

    @Override
    CategoryDTO convert(Category source);

}