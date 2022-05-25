package com.princesan.advertisementmanagerbackend.services.banners;

import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.services.CrudService;

import java.util.List;

public interface BannerService extends CrudService<BannerDto> {

    List<BannerDto> searchByName(String searchRequest);
}
