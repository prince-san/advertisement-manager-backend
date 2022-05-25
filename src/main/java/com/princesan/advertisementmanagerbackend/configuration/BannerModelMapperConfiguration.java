package com.princesan.advertisementmanagerbackend.configuration;

import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BannerModelMapperConfiguration {

    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public TypeMap<BannerDto, Banner> getBannerDtoBannerTypeMap() {
        TypeMap<BannerDto, Banner> typeMap = modelMapper.createTypeMap(BannerDto.class, Banner.class);
        return typeMap;
    }

    @Bean
    public TypeMap<Banner, BannerDto> getBannerBannerDtoTypeMap() {
        TypeMap<Banner, BannerDto> typeMap = modelMapper.createTypeMap(Banner.class, BannerDto.class);
        return typeMap;
    }
}
