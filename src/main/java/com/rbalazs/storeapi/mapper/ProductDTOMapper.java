package com.rbalazs.storeapi.mapper;

import com.rbalazs.storeapi.dto.ProductDTO;
import com.rbalazs.storeapi.model.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

/**
 * Converts a given {@link ProductDTO} into a {@link Product} Model via Spring Converter
 */
@Mapper(componentModel = "spring")
public interface ProductDTOMapper extends Converter<ProductDTO, Product> {

    @Override
    Product convert(ProductDTO source);

}