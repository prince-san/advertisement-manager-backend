package com.princesan.advertisementmanagerbackend.services.category;

import com.princesan.advertisementmanagerbackend.exceptions.NoSuchEntityException;
import com.princesan.advertisementmanagerbackend.model.dtos.CategoryDto;
import com.princesan.advertisementmanagerbackend.model.entities.Banner;
import com.princesan.advertisementmanagerbackend.model.entities.Category;
import com.princesan.advertisementmanagerbackend.repositories.BannerRepository;
import com.princesan.advertisementmanagerbackend.repositories.CategoryRepository;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final BannerRepository bannerRepository;
    private final TypeMap<CategoryDto, Category> categoryDtoCategoryTypeMap;
    private final TypeMap<Category, CategoryDto> categoryCategoryDtoTypeMap;

    public CategoryServiceImpl(CategoryRepository categoryRepository, BannerRepository bannerRepository, TypeMap<CategoryDto, Category> categoryDtoCategoryTypeMap, TypeMap<Category, CategoryDto> categoryCategoryDtoTypeMap) {
        this.categoryRepository = categoryRepository;
        this.bannerRepository = bannerRepository;
        this.categoryDtoCategoryTypeMap = categoryDtoCategoryTypeMap;
        this.categoryCategoryDtoTypeMap = categoryCategoryDtoTypeMap;
    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryDtoCategoryTypeMap.map(categoryDto);
        return categoryCategoryDtoTypeMap.map(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categoryRepository.findAllByDeletedNotLike(true)) {
            categoryDtoList.add(categoryCategoryDtoTypeMap.map(category));
        }
        return categoryDtoList;
    }

    @Override
    public CategoryDto get(Long id) {
        return categoryCategoryDtoTypeMap.map(categoryRepository
                .findCategoryByIdAndDeletedIsNotLike(id, true)
                .orElseThrow(NoSuchEntityException::new));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Category category = categoryDtoCategoryTypeMap.map(categoryDto);
        return categoryCategoryDtoTypeMap.map(categoryRepository.save(category));
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository
                .findCategoryByIdAndDeletedIsNotLike(id, true)
                .orElseThrow(NoSuchEntityException::new);
        List<Banner> banners = bannerRepository.findBannersByCategoriesContainsAndDeletedIsNotLike(
                category, true);
        if (!banners.isEmpty()) throw new RuntimeException("Can't delete category with existing banners.");
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryDto> searchByName(String searchRequest) {
        return null;
    }
}
