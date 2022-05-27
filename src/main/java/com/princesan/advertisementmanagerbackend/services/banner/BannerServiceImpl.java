package com.princesan.advertisementmanagerbackend.services.banner;

import com.princesan.advertisementmanagerbackend.exceptions.NoSuchEntityException;
import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import com.princesan.advertisementmanagerbackend.repositories.BannerRepository;
import com.princesan.advertisementmanagerbackend.repositories.CategoryRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final CategoryRepository categoryRepository;
    private final TypeMap<BannerDto, Banner> bannerDtoBannerTypeMap;
    private final TypeMap<Banner, BannerDto> bannerBannerDtoTypeMap;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository,
                             CategoryRepository categoryRepository, TypeMap<BannerDto, Banner> bannerDtoBannerTypeMap,
                             TypeMap<Banner, BannerDto> bannerBannerDtoTypeMap) {
        this.bannerRepository = bannerRepository;
        this.categoryRepository = categoryRepository;
        this.bannerDtoBannerTypeMap = bannerDtoBannerTypeMap;
        this.bannerBannerDtoTypeMap = bannerBannerDtoTypeMap;
    }

    @Override
    public BannerDto create(BannerDto bannerDto) {
        Banner banner = bannerDtoBannerTypeMap.map(bannerDto);
        List<Category> categories =
                categoryRepository.findDistinctByRequestIdInAndDeletedIsFalse(bannerDto.getCategoryRequestIds());
        banner.setCategories(categories);
        return bannerBannerDtoTypeMap.map(bannerRepository.save(banner));
    }

    @Override
    public List<BannerDto> getAll() {
        List<BannerDto> bannerDtoList = new ArrayList<>();
        for (Banner banner : bannerRepository.findAllByDeletedIsFalse()) {
            bannerDtoList.add(bannerBannerDtoTypeMap.map(banner));
        }
        return bannerDtoList;
    }

    @Override
    public BannerDto get(Long id) {
        return bannerBannerDtoTypeMap.map(bannerRepository
                .findBannerByIdAndDeletedIsFalse(id)
                .orElseThrow(RuntimeException::new));
    }

    @Override
    public BannerDto update(Long id, BannerDto bannerDto) {
        Banner banner = bannerRepository.findBannerByIdAndDeletedIsFalse(id).orElseThrow(
                NoSuchEntityException::new
        );
        List<Category> categories =
                categoryRepository.findDistinctByRequestIdInAndDeletedIsFalse(bannerDto.getCategoryRequestIds());
        banner.setCategories(categories);
        banner.setName(bannerDto.getName());
        banner.setPrice(new BigDecimal(bannerDto.getPrice()));
        banner.setTextField(bannerDto.getTextField());
        return bannerBannerDtoTypeMap.map(bannerRepository.save(banner));
    }

    @Override
    public void delete(Long id) {
        Banner banner = bannerRepository.findById(id).orElseThrow(RuntimeException::new);
        banner.setDeleted(true);
        bannerRepository.save(banner);
    }

    @Override
    public List<BannerDto> searchByName(String searchRequest) {
        List<BannerDto> bannerDtoList = new ArrayList<>();
        for (Banner banner : bannerRepository
                .findBannersByNameContainsIgnoreCaseAndDeletedIsFalse(searchRequest)) {
            bannerDtoList.add(bannerBannerDtoTypeMap.map(banner));
        }
        return bannerDtoList;
    }
}
