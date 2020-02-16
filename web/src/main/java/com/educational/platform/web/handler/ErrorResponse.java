package com.educational.platform.web.handler;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * Represents error response.
 */
@RequiredArgsConstructor
@Data
public class ErrorResponse {

    private final List<String> errors;

    public ErrorResponse(String error) {
        this(error != null ? Collections.singletonList(error) : Collections.emptyList());
    }
}
