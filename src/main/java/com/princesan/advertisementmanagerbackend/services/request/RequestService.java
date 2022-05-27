package com.princesan.advertisementmanagerbackend.services.request;

import com.princesan.advertisementmanagerbackend.exceptions.request.RequestException;
import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;

import java.util.List;

public interface RequestService {

    BannerDto requestBanner(String userAgent, String ip, List<String> categories) throws RequestException;
}
