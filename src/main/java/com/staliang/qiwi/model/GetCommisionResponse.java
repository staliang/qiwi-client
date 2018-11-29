package com.staliang.qiwi.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetCommisionResponse {
    private Commission qwCommission;

    @Data
    public static class Commission {
        private BigDecimal amount;
    }
}
