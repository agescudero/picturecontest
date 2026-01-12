package com.application.picturecontest.domain.model.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record ContestPrice(BigDecimal amount, Currency currency) {

    private static final int SCALE = 2;

    public ContestPrice(BigDecimal amount, Currency currency) {
        Objects.requireNonNull(amount, "amount must not be null");
        Objects.requireNonNull(currency, "currency must not be null");

        this.amount = amount.setScale(SCALE, RoundingMode.HALF_UP);

        if (this.amount.signum() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContestPrice that)) return false;
        return amount.compareTo(that.amount) == 0 && currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount.stripTrailingZeros(), currency);
    }
}
