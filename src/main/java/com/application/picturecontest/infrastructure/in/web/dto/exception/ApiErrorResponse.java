package com.application.picturecontest.infrastructure.in.web.dto.exception;

import java.util.List;

public record ApiErrorResponse(
        ErrorCode errorCode,
        String message,
        List<ApiErrorDetail> details
) {}
