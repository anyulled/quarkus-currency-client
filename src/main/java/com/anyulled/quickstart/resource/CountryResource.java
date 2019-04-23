package com.anyulled.quickstart.resource;

import com.anyulled.quickstart.model.Country;
import com.anyulled.quickstart.service.CountryService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/country")
public class CountryResource {

    @Inject
    CountryService countryService;

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Country getCountry(@PathParam("name") String name) {
        log.info("Controller:: getting country information for {}", name);
        Country country = null;
        try {
            country = countryService.getByName(name);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return country;
    }
}
