package com.princesan.advertisementmanagerbackend.exceptions.request;

public class NoNewMatchingBannersForTodayException extends RequestException {

    @Override
    public String getMessage() {
        return "All matching banners were already shown today";
    }
}
