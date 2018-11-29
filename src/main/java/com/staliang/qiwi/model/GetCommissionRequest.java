package com.staliang.qiwi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)
public class GetCommissionRequest {
    private final String account;
    private final PaymentMethod paymentMethod = new PaymentMethod();
    private final PurchaseTotals purchaseTotals;

    public GetCommissionRequest(String account, BigDecimal amount) {
        this.account = account;
        this.purchaseTotals = new PurchaseTotals(new PurchaseTotals.Total(amount));
    }

    @Data
    private static class PaymentMethod {
        private final String type = "Account";
        private final String accountId = "643";
    }

    @Data
    private static class PurchaseTotals {
        private final Total total;

        @Data
        private static class Total {
            private final BigDecimal amount;
            private final String currency = "643";

            Total(BigDecimal amount) {
                this.amount = amount;
            }
        }
    }
}
