package com.anyulled.quickstart.service.impl;

import com.anyulled.quickstart.client.rest.CountryRestClient;
import com.anyulled.quickstart.model.Country;
import com.anyulled.quickstart.service.CountryService;
import com.anyulled.quickstart.utils.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import java.util.List;

@Slf4j
@ApplicationScoped
public class CountryServiceImpl implements CountryService {

    @Inject
    @RestClient
    CountryRestClient countryRestClient;


    @Override
    public Country getByName(String name) {
        log.info("Service:: Calling rest client");
        Country country = null;
        try {
            log.info("Country to look for {}", name);
            if (CacheHelper.getCountryFromCacheManager().containsKey(name)) {
                log.info("Cache found");
                country = CacheHelper.getCountryFromCacheManager().get(name);
            } else {
                log.info("calling rateRestClient");
                final List<Country> countryList = countryRestClient.getByName(name);
                if (!countryList.isEmpty()) {
                    country = countryList.get(0);
                    CacheHelper.getCountryFromCacheManager().put(name, country);
                } else {
                    throw new WebApplicationException("country not found");
                }
            }
            log.info("{}'s capital is {}", country.getName(), country.getCapital());
        } catch (WebApplicationException e) {
            log.error("Error consulting country {}", name);
            log.error(e.getLocalizedMessage(), e);
        }
            return country;
    }
}
