package com.princesan.advertisementmanagerbackend.repositories;

import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    Optional<Banner> findBannerByIdAndDeletedIsFalse(Long id);

    List<Banner> findBannersByNameContainsIgnoreCaseAndDeletedIsFalse(String name);

    List<Banner> findBannersByCategoriesContainsAndDeletedIsNotLike(
            @NotNull Category category, @NotNull Boolean deleted);

    List<Banner> findAllByDeletedIsFalse();

    List<Banner> findDistinctByCategoriesInAndDeletedIsFalse(List<Category> categories);
}
