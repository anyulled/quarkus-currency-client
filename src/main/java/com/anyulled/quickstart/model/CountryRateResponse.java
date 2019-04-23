package com.anyulled.quickstart.model;

import com.anyulled.quickstart.model.Country;
import com.anyulled.quickstart.model.Rate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryRateResponse {
    private Country country;
    private Rate rate;
}
