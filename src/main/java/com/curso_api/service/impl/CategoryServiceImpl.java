package com.curso_api.service.impl;

import com.curso_api.dto.SaveCategory;
import com.curso_api.exception.ObjectNotFoundException;
import com.curso_api.persistence.entity.Category;
import com.curso_api.persistence.repository.ICategoryRepository;
import com.curso_api.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public Category createOne(SaveCategory saveCategory) {
        Category category = Category.builder()
                .name(saveCategory.getName())
                .status(Category.CategoryStatus.ENABLED)
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category updateById(Long categoryId, SaveCategory saveCategory) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("La categoria no ha sido encontrada con el id " + categoryId));
        categoryFromDB.setName(saveCategory.getName());
        return categoryRepository.save(categoryFromDB);
    }

    @Override
    public Category disabledById(Long categoryId) {
        Category categoryFromDB = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Categorio no encontrado con el id "+ categoryId));
        categoryFromDB.setStatus(Category.CategoryStatus.DISABLED);

        return categoryRepository.save(categoryFromDB);
    }
}
