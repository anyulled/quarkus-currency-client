package com.anyulled.quickstart.model;

import lombok.Data;

import java.util.List;

@Data
public class Country {
    private String name;
    private String alpha2Code;
    private String capital;
    private List<Currency> currencies;

    @Data
    public static class Currency{
        private String code;
        private String name;
        private String symbol;
    }
}
