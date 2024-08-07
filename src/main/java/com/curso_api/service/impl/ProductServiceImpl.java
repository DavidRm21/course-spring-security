package com.curso_api.service.impl;

import com.curso_api.dto.SaveProduct;
import com.curso_api.exception.ObjectNotFoundException;
import com.curso_api.persistence.entity.Category;
import com.curso_api.persistence.entity.Product;
import com.curso_api.persistence.repository.IProductRepository;
import com.curso_api.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product createOne(SaveProduct saveProduct) {
        Product product = Product.builder()
                .name(saveProduct.getName())
                .price(saveProduct.getPrice())
                .status(Product.ProductStatus.ENABLED)
                .build();

        Category category = Category.builder()
                .id(saveProduct.getCategoryId())
                .build();

        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product updateById(Long productId, SaveProduct saveProduct) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Producto no encontrado con el id "+ productId));
        productFromDB.setName(saveProduct.getName());
        productFromDB.setPrice(saveProduct.getPrice());

        Category category = Category.builder()
                .id(saveProduct.getCategoryId())
                .build();

        productFromDB.setCategory(category);

        return productRepository.save(productFromDB);
    }

    @Override
    public Product disabledById(Long productId) {
        Product productFromDB = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Producto no encontrado con el id "+ productId));
        productFromDB.setStatus(Product.ProductStatus.DISABLED);

        return productRepository.save(productFromDB);
    }
}
