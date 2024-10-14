package com.rbalazs.storeapi.model;

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