package com.staliang.qiwi.model;

import lombok.Data;

@Data
public class TransferResponse {
    private Long id;
    private String terms;
    private Fields fields;
    private Sum sum;
    private String source;
    private Transaction transaction;

    @Data
    private class Transaction {
        private String id;
        private TransactionState state;

        @Data
        private class TransactionState {
            private String code;
        }
    }
}
