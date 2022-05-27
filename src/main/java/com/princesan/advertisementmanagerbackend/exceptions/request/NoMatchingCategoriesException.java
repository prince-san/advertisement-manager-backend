package com.princesan.advertisementmanagerbackend.exceptions.request;

public class NoMatchingCategoriesException extends RequestException {

    @Override
    public String getMessage() {
        return "Could not find categories with given requestIds";
    }
}
