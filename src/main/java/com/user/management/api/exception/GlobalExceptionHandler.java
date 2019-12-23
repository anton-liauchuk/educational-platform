package com.user.management.api.exception;

import com.user.management.api.dto.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Bean
    public ErrorAttributes errorAttributes() {
        // Hide exception field in the return object
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
                Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
                errorAttributes.remove("exception");
                return errorAttributes;
            }
        };
    }

    /**
     * Handle resource not found exception.
     *
     * @param exception an exception
     * @return an error message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(final ResourceNotFoundException exception) {
        LOGGER.warn("Resource not found", exception);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDTO.aResponseDTO(exception.getMessage()));
    }

    // todo review handler for ConstraintViolationException
    @ExceptionHandler(value = {ConstraintViolationException.class, ValidationException.class})
    public ResponseEntity<ErrorResponseDTO> validationError(Exception exception) {
        LOGGER.warn("ConstraintViolationException/ValidationException", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.aResponseDTO(exception.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.BAD_REQUEST.value(), "Something went wrong");
    }

}
