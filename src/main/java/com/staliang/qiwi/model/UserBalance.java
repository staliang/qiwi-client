package com.staliang.qiwi.model;

import lombok.Data;

import java.util.List;

@Data
public class UserBalance {
    private List<Account> accounts;

    @Data
    public static class Account {
        private String alias;
        private String fsAlias;
        private String title;
        private Boolean hasBalance;
        private Integer currency;
        private AccountType type;
        private Sum balance;

        @Data
        public static class AccountType {
            private String id;
            private String title;
        }

    }
}
