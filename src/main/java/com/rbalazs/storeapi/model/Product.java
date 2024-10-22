package com.rbalazs.storeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

/**
 * Represents a given Product, a Product it´s associated to an specific {@link Category}.
 *
 * @author Rodrigo Balazs
 */
@Entity
@Getter
@Setter
public class Product extends Base {

    private String name;
    private Double price;

    /** Empty Constructor required by JPA / Hibernate. */
    public Product() {}

    /**
     * Creates a new Product.
     *
     * @param theName the product name, cannot be null nor empty.
     * @param thePrice the product price
     * @param theCategory the category associated to the product, cannot be null.
     */
    public Product(final String theName, final Double thePrice, final Category theCategory) {
        Validate.notEmpty(theName, "The product name cannot be null nor empty");
        Validate.notNull(theCategory, "The category cannot be null");
        name = theName;
        price = thePrice;
        category = theCategory;
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    /* workaround to avoid "org.springframework.http.converter.HttpMessageNotWritableException: Could not write
    JSON: Document nesting depth (1001) exceeds the maximum allowed" during REST API calls due circular dependency
    between Category and Product */
    @JsonIgnore
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price)
                && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, price, category);
    }

    @Override
    public String toString() {
        return "Product{" + "name='" + name + '\'' + ", price=" + price + ", category=" + category + '}';
    }
}