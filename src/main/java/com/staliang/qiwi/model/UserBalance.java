package com.staliang.qiwi.model;

import lombok.Data;

import java.util.List;

@Data
public class UserBalance {
    private List<Account> accounts;
}
