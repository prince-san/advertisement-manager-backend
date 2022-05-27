package com.princesan.advertisementmanagerbackend.controllers;

import com.princesan.advertisementmanagerbackend.exceptions.request.RequestException;
import com.princesan.advertisementmanagerbackend.model.dtos.BannerDto;
import com.princesan.advertisementmanagerbackend.services.request.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("bid")
    public BannerDto requestBanner(@RequestHeader("User-Agent") String userAgent,
                                   @RequestParam List<String> categories,
                                   HttpServletRequest httpServletRequest) throws RequestException {
        return requestService.requestBanner(userAgent, httpServletRequest.getRemoteAddr(), categories);
    }
}
