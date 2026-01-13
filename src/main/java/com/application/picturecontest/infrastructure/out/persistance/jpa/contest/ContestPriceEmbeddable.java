package com.application.picturecontest.infrastructure.out.persistance.jpa.contest;

import com.application.picturecontest.domain.model.valueobject.Currency;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Embeddable
public class ContestPriceEmbeddable {

    @Column(name = "price_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_currency", nullable = false, length = 3)
    private Currency currency;

    protected ContestPriceEmbeddable() {
        // JPA
    }

    public ContestPriceEmbeddable(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
