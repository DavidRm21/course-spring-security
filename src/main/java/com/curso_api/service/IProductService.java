package com.curso_api.service;

import com.curso_api.dto.SaveProduct;
import com.curso_api.persistence.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface IProductService {
    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long productId);

    Product createOne(SaveProduct saveProduct);

    Product updateById(Long productId, SaveProduct saveProduct);

    Product disabledById(Long productId);
}
