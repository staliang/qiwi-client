package com.staliang.qiwi.exception;

import com.staliang.qiwi.exception.model.QiwiError;
import lombok.Data;

@Data
public class QiwiServiceException extends RuntimeException {
    private final QiwiError errorInfo;

    public QiwiServiceException(QiwiError errorInfo) {
        this.errorInfo = errorInfo;
    }
}
