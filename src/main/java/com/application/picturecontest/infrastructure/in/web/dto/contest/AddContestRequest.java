package com.application.picturecontest.infrastructure.in.web.dto.contest;

import com.application.picturecontest.domain.model.valueobject.Currency;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record AddContestRequest(

        @NotNull(message = "amount is required")
        @Digits(integer = 10, fraction = 2, message = "amount must have max 2 decimals")
        @PositiveOrZero(message = "amount must be >= 0")
        BigDecimal amount,

        @NotNull(message = "currency is required")
        Currency currency

) {
}


