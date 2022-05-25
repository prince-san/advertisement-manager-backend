package com.princesan.advertisementmanagerbackend.repositories;

import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByDeletedNotLike(@NotNull Boolean deleted);

    Optional<Category> findCategoryByIdAndDeletedIsNotLike(Long id, @NotNull Boolean deleted);
}