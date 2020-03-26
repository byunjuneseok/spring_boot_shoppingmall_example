package com.mixcape.backend.entity;

import com.mixcape.backend.exception.InsufficientStockException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class ProductItemSku {
    private UUID id;
    private String code;
    private String text;
    private long stockAmount;

    private ProductItemSku() {}

    private ProductItemSku(String code, String text, long stockAmount) {
        setId(UUID.randomUUID());
        setCode(Objects.requireNonNull(code));
        setText(Objects.requireNonNull(text));
        setStockAmount(stockAmount);
    }

    public void refillBy(long amount) {
        this.stockAmount += amount;
    }

    public void reduceStockBy(long amount) {
        if (this.stockAmount < amount) {
            throw new InsufficientStockException(getId());
        }
        this.stockAmount -= amount;
    }

    public void verifyAvailability(long amount) {
        if (this.stockAmount < amount) {
            throw new InsufficientStockException(getId());
        }
    }
}
