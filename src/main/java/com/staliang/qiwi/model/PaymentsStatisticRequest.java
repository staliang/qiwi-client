package com.staliang.qiwi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Data
@Accessors(chain = true)
public class PaymentsStatisticRequest {
    private final Date startDate;
    private final Date endDate;
    private PaymentOperation operation;
    private PaymentSource[] sources;


    @Override
    public String toString() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Map<String, Object> params = new HashMap<>();
        params.put("startDate", format.format(startDate));
        params.put("endDate", format.format(endDate));
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
