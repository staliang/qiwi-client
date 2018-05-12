package com.staliang.qiwi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Data
@Accessors(chain = true)
public class PaymentsHistoryRequest {
    private final int rows;
    private PaymentOperation operation;
    private PaymentSource[] sources;

    @Override
    public String toString() {
        Map<String, Object> params = new HashMap<>();
        params.put("rows", rows);
        params.put("operation", operation);
        if (sources != null) {
            for (int i = 0; i < sources.length; i++) {
                params.put("sources[" + i + "]", sources[i]);
            }
        }

        return params.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(joining("&"));
    }
}
