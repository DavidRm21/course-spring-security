package com.curso_api.service;

import com.curso_api.dto.SaveCategory;
import com.curso_api.persistence.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long categoryId);

    Category createOne(SaveCategory saveCategory);

    Category updateById(Long categoryId, SaveCategory saveCategory);

    Category disabledById(Long categoryId);
}
