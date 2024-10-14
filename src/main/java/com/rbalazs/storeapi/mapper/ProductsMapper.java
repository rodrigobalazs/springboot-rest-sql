package com.rbalazs.storeapi.mapper;

import com.rbalazs.storeapi.dto.ProductDTO;
import com.rbalazs.storeapi.model.Product;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * Converts a list of {@link Product} into a list of {@link ProductDTO} via Spring Converter
 */
@Mapper(componentModel = "spring")
public interface ProductsMapper extends Converter<List<Product>, List<ProductDTO>> {

    @Override
    List<ProductDTO> convert(List<Product> source);

}