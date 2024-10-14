package com.rbalazs.storeapi.mapper;

import com.rbalazs.storeapi.dto.ProductDTO;
import com.rbalazs.storeapi.model.Product;
import org.springframework.core.convert.converter.Converter;
import org.mapstruct.Mapper;

/**
 * Converts a given {@link Product} Model into a {@link ProductDTO} via Spring Converter
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends Converter<Product, ProductDTO> {

    @Override
    ProductDTO convert(Product source);

}