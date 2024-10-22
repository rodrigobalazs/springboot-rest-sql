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

    @ManyToOne
    @JoinColumn(name = "category_id")
    /* This is required to avoid during GET API Calls this exception =>
    "org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Document nesting depth
    exceeds the maximum allowed" which is caused by a circular dependency between Category and Product */
    @JsonIgnore
    private Category category;

    /** Empty Constructor required by JPA / Hibernate. */
    public Product() {}

    /**
     * Creates a new Product.
     *
     * @param theName the product name, cannot be null nor empty.
     * @param thePrice the product price
     */
    public Product(final String theName, final Double thePrice) {
        Validate.notEmpty(theName, "The product name cannot be null nor empty");
        name = theName;
        price = thePrice;
    }

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