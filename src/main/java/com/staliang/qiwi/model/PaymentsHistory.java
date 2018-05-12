package com.staliang.qiwi.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PaymentsHistory {
    private List<Payment> data;

    @Data
    public static class Payment {
        private Long txnId;
        private Long personId;
        private Date date;
        private Integer errorCode;
        private String error;
        private PaymentType type;
        private PaymentStatus status;
        private String statusText;
        private String trmTxnId;
        private String account;
        private Sum sum;
        private Sum commission;
        private Sum total;
        private Provider provider;
        private String comment;
    }
}
