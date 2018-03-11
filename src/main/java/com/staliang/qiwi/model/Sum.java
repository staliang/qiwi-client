package com.staliang.qiwi.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Sum {
    private BigDecimal amount;
    private String currency;
}
