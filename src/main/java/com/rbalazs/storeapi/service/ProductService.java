package com.rbalazs.storeapi.service;

import com.rbalazs.storeapi.dto.ProductDTO;
import com.rbalazs.storeapi.exception.EntityNotFoundException;
import com.rbalazs.storeapi.model.Product;
import com.rbalazs.storeapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Product Service
 *
 * @author Rodrigo Balazs
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /** Spring Conversion Service, used to convert between DTO / Model. */
    private final ConversionService conversionService;

    @Autowired
    public ProductService(ProductRepository productRepository, ConversionService conversionService) {
        this.productRepository = productRepository;
        this.conversionService = conversionService;
    }

    /**
     * Retrieves a list with all the Products
     *
     * @return a list of {@link ProductDTO}
     */
    public List<ProductDTO> getProducts() {
        return conversionService.convert(productRepository.findAll(), List.class);
    }

    /**
     * Retrieves a Product by the ID given as parameter.
     *
     * @param id the product ID to retrieve
     * @return a {@link ProductDTO}
     */
    public ProductDTO getProductById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(id);
        }
        return conversionService.convert(result.get(), ProductDTO.class);
    }
}
