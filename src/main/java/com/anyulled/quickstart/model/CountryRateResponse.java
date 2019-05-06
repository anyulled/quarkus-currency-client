package com.anyulled.quickstart.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryRateResponse {
    private Country country;
    private Rate rate;
}
