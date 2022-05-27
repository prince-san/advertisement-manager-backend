package com.princesan.advertisementmanagerbackend.exceptions.request;

public class NoMatchingBannersException extends RequestException {

    @Override
    public String getMessage() {
        return "Could not find banners under given categories";
    }
}
