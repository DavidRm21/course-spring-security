package com.curso_api.persistence.repository;

import com.curso_api.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
