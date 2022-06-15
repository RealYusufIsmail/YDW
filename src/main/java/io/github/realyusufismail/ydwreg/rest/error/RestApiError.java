package io.github.realyusufismail.ydwreg.rest.error;

import java.io.IOException;

public enum RestApiError {
    OK(200, "The request completed successfully."),
    CREATED(201, "The entity was created successfully."),
    NO_CONTENT(204, "The request completed successfully, but no content was returned."),
    BAD_REQUEST(400, "The request was improperly formatted, or the server couldn't understand it."),
    UNAUTHORIZED(401, "The Authorization header was missing or invalid."),
    FORBIDDEN(403, "The Authorization token you passed did not have permission to the resource."),
    NOT_FOUND(404, "The resource was not found."),
    METHOD_NOT_ALLOWED(405, "The HTTP method used is not valid for the location specified."),
    TOO_MANY_REQUESTS(429, "You have made too many requests in a given amount of time."),
    GATEWAY_UNAVAILABLE(502,
            "There was not a gateway available to process your request. Wait a bit and retry."),
    INTERNAL_SERVER_ERROR(500, "The server encountered an unexpected error.");

    private final int code;
    private final String message;

    RestApiError(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static RestApiError fromCode(int code) {
        for (RestApiError error : RestApiError.values()) {
            if (error.code == code) {
                return error;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
