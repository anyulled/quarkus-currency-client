package com.anyulled.quickstart.resource;

import com.anyulled.quickstart.service.InstagramService;
import com.anyulled.quickstart.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/instagram")
public class InstagramResource {

    @Inject
    InstagramService service;

    @GET
    @Path("/response")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getResponseCode(@QueryParam("code") String code) throws BusinessException {
        if (code != null) {
            log.info("saving auth code {}", code);
            service.saveCode(code);
            if (!service.isTokenSet()) {
                log.info("obtaining access token for code {}", code);
                service.obtainAccessToken();
            }
            log.info("Getting user info");
            return Response.ok()
                    .entity(service.getUserInfo())
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Code not present")
                    .build();
        }
    }

    @GET
    @Path("/getAccessToken")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccessToken(@QueryParam("code") String code) throws BusinessException {
        return Response.ok(service.obtainAccessToken(code)).build();
    }
}
