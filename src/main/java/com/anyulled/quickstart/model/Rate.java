package com.anyulled.quickstart.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

/**
 * Author: anyulled
 *
 */
@Data
public class Rate {
    private String base;
    private Map<String, Double> rates;
    private LocalDate date;
}