package com.anyulled.quickstart.client.rest;

import com.anyulled.quickstart.model.Rate;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@RegisterRestClient
public interface RateRestClient {

    @GET
    @Path("/latest")
    @Produces(MediaType.APPLICATION_JSON)
    Rate getRates(@QueryParam("base") String base);
}
