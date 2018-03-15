package com.staliang.qiwi.model;

import lombok.Data;

@Data
public class Account {
    private String alias;
    private String fsAlias;
    private String title;
    private Boolean hasBalance;
    private Integer currency;
    private AccountType type;
    private Sum balance;
}
