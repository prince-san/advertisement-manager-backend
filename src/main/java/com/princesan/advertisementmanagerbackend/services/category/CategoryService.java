package com.princesan.advertisementmanagerbackend.services.category;

import com.princesan.advertisementmanagerbackend.model.dtos.CategoryDto;
import com.princesan.advertisementmanagerbackend.services.CrudService;

import java.util.List;

public interface CategoryService extends CrudService<CategoryDto> {

    List<CategoryDto> searchByName(String searchRequest);
}
