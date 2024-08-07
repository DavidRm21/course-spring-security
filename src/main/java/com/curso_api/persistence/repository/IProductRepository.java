package com.curso_api.persistence.repository;

import com.curso_api.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
