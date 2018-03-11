package com.staliang.qiwi.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Payment {
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
}
