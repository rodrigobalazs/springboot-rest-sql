package com.rbalazs.storeapi.mapper;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.model.Category;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts a given {@link CategoryDTO} into a {@link Category} Model via Spring Converter
 */
@Mapper(componentModel = "spring")
public interface CategoryDTOMapper extends Converter<CategoryDTO, Category> {

    @Override
    Category convert(CategoryDTO source);

}