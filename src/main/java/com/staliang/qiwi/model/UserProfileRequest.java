package com.staliang.qiwi.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Data
@Accessors(chain = true)
public class UserProfileRequest {
    private boolean authInfoEnabled = true;
    private boolean contractInfoEnabled = true;
    private boolean userInfoEnabled = true;

    @Override
    public String toString() {
        Map<String, Boolean> params = new HashMap<>();
        params.put("authInfoEnabled", authInfoEnabled);
        params.put("contractInfoEnabled", contractInfoEnabled);
        params.put("userInfoEnabled", userInfoEnabled);

        return params.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .map(e -> String.format("%s=%s", e.getKey(), e.getValue()))
                .collect(joining("&"));
    }
}
