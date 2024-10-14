package com.rbalazs.storeapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.Validate;

/**
 * Represents a given Category, a Category could have a list of {@link Product} associated.
 *
 * @author Rodrigo Balazs
 */
@Entity
@Getter
@Setter
public class Category extends Base {

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;

    /** Empty Constructor required by JPA / Hibernate. */
    public Category() {}

    /**
     * Creates a new Category.
     *
     * @param theName the category name, cannot be null nor empty.
     */
    public Category(final String theName) {
        Validate.notEmpty(theName, "The category name cannot be null nor empty");
        name = theName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(products, category.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, products);
    }

    @Override
    public String toString() {
        return "Category{" + "name='" + name + '\'' + ", products=" + products + '}';
    }
}