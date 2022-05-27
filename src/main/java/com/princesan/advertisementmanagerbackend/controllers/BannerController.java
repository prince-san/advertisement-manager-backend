package com.princesan.advertisementmanagerbackend.controllers;

import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.services.banner.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping("/")
    public BannerDto create(@RequestBody BannerDto bannerDto) {
        return bannerService.create(bannerDto);
    }

    @GetMapping("/")
    public List<BannerDto> getAll() {
        return bannerService.getAll();
    }

    @GetMapping("/{id}")
    public BannerDto get(@PathVariable Long id) {
        return bannerService.get(id);
    }

    @PutMapping("/{id}")
    public BannerDto update(@PathVariable Long id, @RequestBody BannerDto bannerDto) {
        return bannerService.update(id, bannerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bannerService.delete(id);
    }

    @GetMapping("/search")
    public List<BannerDto> search(@RequestParam("searchRequest") String searchRequest) {
        return bannerService.searchByName(searchRequest);
    }
}
