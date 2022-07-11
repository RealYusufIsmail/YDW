/*
 * Copyright 2022 Yusuf Arfan Ismail and other YDW contributors.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.ydwreg.rest.error;

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
