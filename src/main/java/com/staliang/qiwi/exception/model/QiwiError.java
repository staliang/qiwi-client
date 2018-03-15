package com.staliang.qiwi.exception.model;

import lombok.Data;

import java.util.Map;

@Data
public class QiwiError {
    private String serviceName;
    private String errorCode;
    private String userMessage;
    private Map<String, Object> cause;
}
