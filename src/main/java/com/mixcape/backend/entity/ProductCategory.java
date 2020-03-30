package com.mixcape.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
@ToString(exclude = { "parent" })
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String code;
    private String text;

    private ProductCategory parent;

    private List<ProductCategory> children = new ArrayList<>();

    protected ProductCategory() {}

    public ProductCategory child(String code, String text) {
        val child = create(code, text); {
            child.setParent(this);
        }
        children.add(child);
        return child;
    }

    public static ProductCategory create(String code, String text) {
        val category = new ProductCategory(); {
            category.setId(UUID.randomUUID());
            category.setCode(Objects.requireNonNull(code));
            category.setText(Objects.requireNonNull(text));
        }
        return category;
    }
}
