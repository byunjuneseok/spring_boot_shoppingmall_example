package com.mixcape.backend.repository;

import com.mixcape.backend.entity.Product;
import com.mixcape.backend.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByCode(String code);
    List<Product> findByCategories(ProductCategory category);

}
