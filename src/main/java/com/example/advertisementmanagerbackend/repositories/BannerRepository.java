package com.example.advertisementmanagerbackend.repositories;

import com.example.advertisementmanagerbackend.model.entities.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface BannerRepository extends JpaRepository<Banner, Long> {

    List<Banner> findAllByDeletedNotLike(@NotNull Boolean deleted);

    Optional<Banner> findBannerByIdAndDeletedIsNotLike(Long id, @NotNull Boolean deleted);

    List<Banner> findBannersByNameContainsIgnoreCaseAndDeletedIsNotLike(@NotNull String name,
                                                                        @NotNull Boolean deleted);
}
