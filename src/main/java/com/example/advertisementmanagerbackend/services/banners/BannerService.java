package com.example.advertisementmanagerbackend.services.banners;

import com.example.advertisementmanagerbackend.model.dtos.BannerDto;
import com.example.advertisementmanagerbackend.services.CrudService;

import java.util.List;

public interface BannerService extends CrudService<BannerDto> {

    List<BannerDto> searchByName(String searchRequest);
}
