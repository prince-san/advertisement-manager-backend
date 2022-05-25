package com.princesan.advertisementmanagerbackend.exceptions;

public class NoSuchEntityException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Couldn't find such entity";
    }
}
