package com.anyulled.quickstart.client.rest;

import com.anyulled.quickstart.model.Country;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/v2")
@RegisterRestClient
/**
 * calls https://restcountries.eu/rest/v2/name/{name}
 */
public interface CountryRestClient {

    @GET
    @Path("/name/{name}")
    @Produces("application/json")
    List<Country> getByName(@PathParam("name") String name);
}