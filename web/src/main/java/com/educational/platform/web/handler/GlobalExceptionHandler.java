package com.educational.platform.web.handler;

import com.educational.platform.common.exception.RelatedResourceIsNotResolvedException;
import com.educational.platform.common.exception.ResourceNotFoundException;
import com.educational.platform.common.exception.UnprocessableEntityException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> onConstraintViolationException(ConstraintViolationException e) {
        var errors = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(RelatedResourceIsNotResolvedException.class)
    public ResponseEntity<ErrorResponse> onRelatedResourceIsNotResolvedException(RelatedResourceIsNotResolvedException e) {
        var response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> onResourceNotFoundException(ResourceNotFoundException e) {
        var response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ErrorResponse> onUnprocessableEntityException(UnprocessableEntityException e) {
        var response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> onException(Exception e) {
        var response = new ErrorResponse(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }


}
