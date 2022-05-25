package com.princesan.advertisementmanagerbackend.repositories;

import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    List<Banner> findAllByDeletedNotLike(@NotNull Boolean deleted);

    Optional<Banner> findBannerByIdAndDeletedIsNotLike(Long id, @NotNull Boolean deleted);

    List<Banner> findBannersByNameContainsIgnoreCaseAndDeletedIsNotLike(@NotNull String name,
                                                                        @NotNull Boolean deleted);

    List<Banner> findBannersByCategoriesContainsAndDeletedIsNotLike(
            @NotNull Category category, @NotNull Boolean deleted);
}
