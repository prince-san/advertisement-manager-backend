package com.example.advertisementmanagerbackend.services.category;

import com.example.advertisementmanagerbackend.model.dtos.CategoryDto;
import com.example.advertisementmanagerbackend.services.CrudService;

import java.util.List;

public interface CategoryService extends CrudService<CategoryDto> {

    List<CategoryDto> searchByName(String searchRequest);
}
