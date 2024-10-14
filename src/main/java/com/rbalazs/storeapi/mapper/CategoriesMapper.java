package com.rbalazs.storeapi.mapper;

import com.rbalazs.storeapi.dto.CategoryDTO;
import com.rbalazs.storeapi.model.Category;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * Converts a list of {@link Category} into a list of {@link CategoryDTO} via Spring Converter
 */
@Mapper(componentModel = "spring")
public interface CategoriesMapper extends Converter<List<Category>, List<CategoryDTO>> {

    @Override
    List<CategoryDTO> convert(List<Category> source);

}