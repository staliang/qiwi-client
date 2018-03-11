package com.staliang.qiwi.model;

import lombok.Data;

@Data
public class Provider {
    private Long id;
    private String shortName;
    private String longName;
    private String logoUrl;
    private String description;
    private String keys;
    private String siteUrl;
}
