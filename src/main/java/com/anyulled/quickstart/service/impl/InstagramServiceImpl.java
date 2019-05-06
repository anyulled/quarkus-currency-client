package com.anyulled.quickstart.service.impl;

import com.anyulled.quickstart.client.rest.InstagramRestClient;
import com.anyulled.quickstart.model.AccessTokenResponse;
import com.anyulled.quickstart.model.MediaResponse;
import com.anyulled.quickstart.model.UserResponse;
import com.anyulled.quickstart.service.InstagramService;
import com.anyulled.quickstart.utils.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class InstagramServiceImpl implements InstagramService {

    @Inject
    @RestClient
    InstagramRestClient client;
    String code;
    AccessTokenResponse accessToken;
    @ConfigProperty(name = "client.id")
    String clientId;
    @ConfigProperty(name = "client.secret")
    String clientSecret;
    @ConfigProperty(name = "grant.type")
    String grantType;
    @ConfigProperty(name = "redirect.uri")
    String redirectURI;

    @Override
    public void saveCode(String code) {
        this.code = code;
    }

    @Override
    public String obtainAccessToken() throws BusinessException {
        log.info("getting access token for code {}", code);
        return Optional
                .ofNullable(client.getAccessToken(clientId, clientSecret, grantType, redirectURI, code))
                .map(AccessTokenResponse::getAccessToken)
                .orElseThrow(BusinessException::new);
    }

    @Override
    public String obtainAccessToken(String code) throws BusinessException {
        log.info("Service :: getting access token for code {} ::", code);
        return Optional
                .ofNullable(client.getAccessToken(clientId, clientSecret, grantType, redirectURI, code))
                .map(AccessTokenResponse::getAccessToken)
                .orElseThrow(BusinessException::new);
    }

    @Override
    public UserResponse getUserInfo() throws BusinessException {
        log.info("calling instagram API for {} information with access token {}", accessToken.getUser().getUsername(), accessToken.getAccessToken());
        return Optional
                .ofNullable(client.getUserInfo(accessToken.getAccessToken()))
                .orElseThrow(BusinessException::new);
    }

    @Override
    public MediaResponse getMediaInfo() throws BusinessException {
        log.info("calling instagram API for {} media information with access token {}", accessToken.getUser().getUsername(), accessToken.getAccessToken());
        return Optional
                .ofNullable(client.getUserMedia(accessToken.getAccessToken()))
                .orElseThrow(BusinessException::new);
    }

    @Override
    public Boolean isTokenSet() {
        return Objects.nonNull(accessToken);
    }

}
