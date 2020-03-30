package com.mixcape.backend.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String code;
    private String name;
    private Set<ProductItem> items = new HashSet<>();
    private Set<ProductCategory> categories = new HashSet<>();

    private Product() {}

    private Product(String code, String name, ProductItem base, Set<ProductItem> additives) {
        this.id = UUID.randomUUID();
        this.code = Objects.requireNonNull(code);
        this.name = Objects.requireNonNull(name);
        this.items.add(Objects.requireNonNull(base));
        if (Objects.nonNull(additives)) {
            this.items.addAll(additives);
        }
    }

    public Product link(ProductCategory category) {
        categories.add(category);
        return this;
    }

    public BigDecimal calculatePrice() {
        return items.stream()
                .map(ProductItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
