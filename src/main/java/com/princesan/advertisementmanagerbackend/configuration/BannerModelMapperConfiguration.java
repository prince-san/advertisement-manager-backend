package com.princesan.advertisementmanagerbackend.configuration;

import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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
        Converter<List<Category>, List<String>> converter =
                list -> list.getSource().stream().map(Category::getRequestId).toList();
        typeMap.addMappings(mapper ->
                mapper.using(converter).map(Banner::getCategories, BannerDto::setCategoryRequestIds));
        return typeMap;
    }
}
