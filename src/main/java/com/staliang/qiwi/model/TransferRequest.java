package com.staliang.qiwi.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransferRequest {
    private String id;
    private Sum sum;
    private PaymentMethod paymentMethod = new PaymentMethod();
    private Fields fields;
    private String comment;

    @Data
    private class PaymentMethod {
        private String type = "Account";
        private String accountId = "643";
    }
}
