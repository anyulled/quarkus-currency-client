package com.anyulled.quickstart.client.rest;

import com.anyulled.quickstart.model.AccessTokenResponse;
import com.anyulled.quickstart.model.MediaResponse;
import com.anyulled.quickstart.model.UserResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * calls https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN
 */
@RegisterRestClient
public interface InstagramRestClient {

    /**
     * calls https://api.instagram.com/oauth/access_token
     *
     * @return access token
     */
    @POST
    @Path("/oauth/access_token")
    @Produces(MediaType.APPLICATION_JSON)
    AccessTokenResponse getAccessToken(@FormParam("client_id") String clientId,
                                       @FormParam("client_secret") String clientSecret,
                                       @FormParam("grant_type") String grantType,
                                       @FormParam("redirect_uri") String redirectURI,
                                       @FormParam("code") String code);

    @GET
    @Path("/v1/users/self/")
    @Produces(MediaType.APPLICATION_JSON)
    UserResponse getUserInfo(@QueryParam("access_token") String accessToken);

    @GET
    @Path("/v1/users/self/media/recent/")
    @Produces(MediaType.APPLICATION_JSON)
    MediaResponse getUserMedia(@QueryParam("access_token") String accessToken);
}