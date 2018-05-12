package com.staliang.qiwi.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentsStatistic {
    private List<Total> incomingTotal;
    private List<Total> outgoingTotal;

    @Data
    public static class Total {
        private BigDecimal amount;
        private String currency;
    }
}
