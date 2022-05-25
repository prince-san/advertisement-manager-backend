package com.princesan.advertisementmanagerbackend.services.banners;

import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.repositories.BannerRepository;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final TypeMap<BannerDto, Banner> bannerDtoBannerTypeMap;
    private final TypeMap<Banner, BannerDto> bannerBannerDtoTypeMap;

    @Autowired
    public BannerServiceImpl(BannerRepository bannerRepository,
                             TypeMap<BannerDto, Banner> bannerDtoBannerTypeMap,
                             TypeMap<Banner, BannerDto> bannerBannerDtoTypeMap) {
        this.bannerRepository = bannerRepository;
        this.bannerDtoBannerTypeMap = bannerDtoBannerTypeMap;
        this.bannerBannerDtoTypeMap = bannerBannerDtoTypeMap;
    }

    @Override
    public BannerDto create(BannerDto bannerDto) {
        Banner banner = bannerDtoBannerTypeMap.map(bannerDto);
        return bannerBannerDtoTypeMap.map(bannerRepository.save(banner));
    }

    @Override
    public List<BannerDto> getAll() {
        List<BannerDto> bannerDtoList = new ArrayList<>();
        for (Banner banner : bannerRepository.findAllByDeletedNotLike(true)) {
            bannerDtoList.add(bannerBannerDtoTypeMap.map(banner));
        }
        return bannerDtoList;
    }

    @Override
    public BannerDto get(Long id) {
        return bannerBannerDtoTypeMap.map(bannerRepository
                .findBannerByIdAndDeletedIsNotLike(id, true)
                .orElseThrow(RuntimeException::new));
    }

    @Override
    public BannerDto update(BannerDto bannerDto) {
        Banner banner = bannerDtoBannerTypeMap.map(bannerDto);
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
                .findBannersByNameContainsIgnoreCaseAndDeletedIsNotLike(searchRequest, true)) {
            bannerDtoList.add(bannerBannerDtoTypeMap.map(banner));
        }
        return bannerDtoList;
    }
}
