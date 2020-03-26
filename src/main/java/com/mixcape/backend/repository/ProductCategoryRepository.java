package com.mixcape.backend.repository;

import com.mixcape.backend.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {

    ProductCategory findByCode(String code);
}
