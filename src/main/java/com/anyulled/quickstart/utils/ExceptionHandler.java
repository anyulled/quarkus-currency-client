package com.anyulled.quickstart.utils;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Slf4j
@Provider
public class ExceptionHandler implements ExceptionMapper<BusinessException> {

    @Override
    public Response toResponse(BusinessException exception) {
        log.error("= Uh Oh, something happened =", exception);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getLocalizedMessage())
                .build();
    }
}
