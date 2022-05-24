package com.example.advertisementmanagerbackend.configuration;

import com.example.advertisementmanagerbackend.model.dtos.CategoryDto;
import com.example.advertisementmanagerbackend.model.entities.Category;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryModelMapperConfiguration {

    private final ModelMapper modelMapper = new ModelMapper();

    @Bean
    public TypeMap<CategoryDto, Category>
}
