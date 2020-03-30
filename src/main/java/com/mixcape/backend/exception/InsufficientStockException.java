package com.mixcape.backend.exception;

import java.util.UUID;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(UUID skuId) {
        super(String.format("Insufficient stock (SKU ID: %s", skuId));
    }
}
