package com.princesan.advertisementmanagerbackend.configuration;

import com.princesan.advertisementmanagerbackend.model.dtos.CategoryDto;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryModelMapperConfiguration {

    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public TypeMap<CategoryDto, Category> getCategoryDtoCategoryTypeMap() {
        return modelMapper.typeMap(CategoryDto.class, Category.class);
    }
    @Bean
    public TypeMap<Category, CategoryDto> getCategoryCategoryDtoTypeMap() {
        return modelMapper.typeMap(Category.class, CategoryDto.class);
    }
}
