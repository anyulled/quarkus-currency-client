package com.anyulled.quickstart.resource;

import com.anyulled.quickstart.model.Rate;
import com.anyulled.quickstart.service.RateService;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Path("/rate")
public class CurrencyResource {

    @Inject
    RateService rateService;

    @GET
    @Path("/{base}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rate getRates(@PathParam("base") String base) {
        log.info("Controller:: getting rates for {}", base);
        return rateService.getRates(base.toUpperCase());
    }

    @GET
    @Path("/convert/{base}/{amount}/{target}")
    @Produces(MediaType.TEXT_PLAIN)
    public BigDecimal convert(@PathParam("base") String base, @PathParam("amount") BigDecimal amount, @PathParam("target") String target) {
        log.info("Controller :: Converting {} {} into {}", amount, base, target);
        BigDecimal result;
        try {
            result = rateService.convert(base.toUpperCase(), amount, target.toUpperCase());
        } catch (IllegalArgumentException e) {
            result = BigDecimal.ZERO;
        }
        return result;
    }

    @POST
    @Path("/convert/{base}/{target}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public List<BigDecimal> convert(@PathParam("base") String base, @PathParam("target") String target, @FormParam("amounts") List<BigDecimal> amounts) {
        log.info("Controller :: Converting {} {} into {}", Arrays.toString(amounts.toArray()), base, target);
        try {
            return rateService.convert(base.toUpperCase(), amounts, target.toUpperCase());
        } catch (IllegalArgumentException e) {
            return amounts;
        }
    }
}
