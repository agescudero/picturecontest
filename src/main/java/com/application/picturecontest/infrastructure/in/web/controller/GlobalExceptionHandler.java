package com.application.picturecontest.infrastructure.in.web.controller;


import com.application.picturecontest.infrastructure.in.web.dto.exception.ApiErrorDetail;
import com.application.picturecontest.infrastructure.in.web.dto.exception.ApiErrorResponse;
import com.application.picturecontest.infrastructure.in.web.dto.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex
    ) {
        List<ApiErrorDetail> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ApiErrorDetail(
                        error.getField(),
                        error.getCode(),           // NOT_BLANK, SIZE, etc.
                        error.getDefaultMessage()
                ))
                .toList();

        ApiErrorResponse response = new ApiErrorResponse(
                ErrorCode.VALIDATION_ERROR,
                "Invalid request",
                details
        );

        return ResponseEntity.badRequest().body(response);
    }
}

