package io.github.realyusufismail.ydwreg.rest.error;

import io.github.realyusufismail.ydwreg.rest.exception.RestApiException;

public class RestApiErrorHandler extends RestApiException {
    public RestApiErrorHandler(String message) {
        super(message);
    }
}
