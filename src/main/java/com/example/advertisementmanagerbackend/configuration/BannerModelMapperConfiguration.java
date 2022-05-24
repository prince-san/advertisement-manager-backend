package com.example.advertisementmanagerbackend.configuration;

import com.example.advertisementmanagerbackend.model.dtos.BannerDto;
import com.example.advertisementmanagerbackend.model.entities.Banner;
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
