package com.mixcape.backend.entity;


import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class ProductItem {
    private UUID id;
    private ProductItemSku sku;
    private String name;
    private BigDecimal price;
    private boolean base;

    private ProductItem() {}

    private ProductItem(ProductItemSku sku, String name, BigDecimal price, boolean base) {
        setId(UUID.randomUUID());
        setSku(Objects.requireNonNull(sku));
        setName(Objects.requireNonNull(name));
        setPrice(price);
        setBase(base);
    }

    public static ProductItem base(ProductItemSku sku, BigDecimal price) {
        return base(sku, sku.getText(), price);
    }

    public static ProductItem base(ProductItemSku sku, String name, BigDecimal price) {
        return new ProductItem(sku, name, price, true);
    }

    public static ProductItem additive(ProductItemSku sku, BigDecimal price) {
        return additive(sku, sku.getText(), price);
    }

    public static ProductItem additive(ProductItemSku sku, String name, BigDecimal price) {
        return new ProductItem(sku, name, price, false);
    }
}
