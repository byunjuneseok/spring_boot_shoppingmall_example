package com.mixcape.backend.entity;

import com.mixcape.backend.exception.InsufficientStockException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class ProductItemSku {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    private String code;
    private String text;
    private long stockAmount;

    protected ProductItemSku() {}

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
