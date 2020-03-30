package com.mixcape.backend.repository;

import com.mixcape.backend.entity.ProductItemSku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductSkuRepository extends JpaRepository<ProductItemSku, UUID> {
    Optional<ProductItemSku> findByCode(String code);
}
