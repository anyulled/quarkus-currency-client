package com.anyulled.quickstart.resource;

import com.anyulled.quickstart.model.Country;
import com.anyulled.quickstart.model.CountryRateResponse;
import com.anyulled.quickstart.model.Rate;
import com.anyulled.quickstart.service.impl.CountryServiceImpl;
import com.anyulled.quickstart.service.impl.RateServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/info")
public class CurrencyConversionResource {

    @Inject
    RateServiceImpl rateService;

    @Inject
    CountryServiceImpl countryService;

    @GET
    @Path("/country/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public CountryRateResponse getCountryRate(@PathParam("name") String name) {
        Country country = countryService.getByName(name);
        Rate rate = null;
        if (country != null && !country.getCurrencies().isEmpty()) {
            final String currencyCode = country.getCurrencies().get(0).getCode();
            rate = rateService.getRates(currencyCode);
        }
        return CountryRateResponse.builder()
                .country(country)
                .rate(rate)
                .build();
    }
}