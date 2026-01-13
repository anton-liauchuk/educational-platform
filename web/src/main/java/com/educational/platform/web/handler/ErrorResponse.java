package com.educational.platform.web.handler;


import java.util.Collections;
import java.util.List;

/**
 * Represents error response.
 */
public record ErrorResponse(List<String> errors) {

    public ErrorResponse(String error) {
        this(error != null ? Collections.singletonList(error) : Collections.emptyList());
    }
}
