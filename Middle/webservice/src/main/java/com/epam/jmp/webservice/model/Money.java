package com.epam.jmp.webservice.model;

import java.math.BigDecimal;

public class Money {

    public static final String CURRENCY_USD = "CURRENCY_USD";
    private String currencyCode;
    private BigDecimal amount;

    public Money() {}

    public Money(BigDecimal amount) {
        currencyCode = CURRENCY_USD;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}