package com.staliang.qiwi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Data
@Accessors(chain = true)
public class SearchPayment {
    private int rows;
    private Operation operation;
    private Source[] sources;

    public enum Operation {
        ALL,
        IN,
        OUT,
        QIWI_CARD
    }

    public enum Source {
        QW_RUB,
        QW_USD,
        QW_EUR,
        CARD,
        MK;
    }

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
