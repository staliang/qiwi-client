package com.staliang.qiwi.model;

import lombok.Data;

import java.util.Date;

@Data
public class TransactionInfo {
    private long txnId;
    private long personId;
    private Date date;
    private int errorCode;
    private String error;
    private PaymentStatus status;
    private PaymentType type;
    private String statusText;
    private long trmTxnId;
    private String account;
    private Sum sum;
    private Sum commission;
    private Sum total;
    private Provider provider;
    private Provider source;
    private String comment;
    private int currencyRate;
}
