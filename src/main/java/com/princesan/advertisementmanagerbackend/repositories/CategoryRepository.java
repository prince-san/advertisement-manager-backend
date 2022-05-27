package com.princesan.advertisementmanagerbackend.repositories;

import com.princesan.advertisementmanagerbackend.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedIsFalse();

    List<Category> findCategoriesByNameContainsIgnoreCaseAndDeletedIsFalse(String name);

    Optional<Category> findCategoryByIdAndDeletedIsFalse(Long id);

    List<Category> findDistinctByRequestIdInAndDeletedIsFalse(List<String> requestId);
}