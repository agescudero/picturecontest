package com.application.picturecontest.infrastructure.in.web.dto.exception;

public record ApiErrorDetail(
        String field,
        String code,
        String message
) {}
