package com.anyulled.quickstart.service.impl;

import com.anyulled.quickstart.client.rest.RateRestClient;
import com.anyulled.quickstart.model.Rate;
import com.anyulled.quickstart.service.RateService;
import com.anyulled.quickstart.utils.CacheHelper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ApplicationScoped
public class RateServiceImpl implements RateService {

    private static final String SERVICE = "Service::";

    @Inject
    @RestClient
    RateRestClient rateRestClient;

    @Override
    public Rate getRates(String base) {
        log.info(SERVICE + " calling rest client");
        Rate rate = null;
        try {
            log.info("looking {} rates ", base);
            if (CacheHelper.getRateFromCacheManager().containsKey(base)) {
                log.info(SERVICE + "cache found");
                rate = CacheHelper.getRateFromCacheManager().get(base);
            } else {
                log.info("calling rateRestClient");
                rate = rateRestClient.getRates(base);
                CacheHelper.getRateFromCacheManager().put(base, rate);
            }
            if (rate.getRates().containsKey("EUR")) {
                log.info(SERVICE + "{} for euro is {}", rate.getBase(), rate.getRates().get("EUR"));
            } else if (rate.getRates().containsKey("USD")) {
                log.info(SERVICE + "{} for US $ is {}", rate.getBase(), rate.getRates().get("USD"));

            }
        } catch (WebApplicationException e) {
            log.error(SERVICE + "error consulting rate {}", base);
            log.error(e.getLocalizedMessage(), e);
        }
        return rate;
    }

    @Override
    public BigDecimal convert(String base, BigDecimal amount, String target) {

        BigDecimal result;
        Rate rate = getRates(base);
        if (rate.getRates().containsKey(target)) {
            final Double targetRate = rate.getRates().get(target);
            log.info("{} rate: {}", target, targetRate);
            result = amount.multiply(BigDecimal.valueOf(targetRate));
        } else {
            throw new IllegalArgumentException("Rate not supported");
        }
        return result;
    }

    @Override
    public List<BigDecimal> convert(@NotNull String base, @NotNull List<BigDecimal> amountList, @NotNull String target) {
        Rate rate = getRates(base);
        if (rate.getRates().containsKey(target)) {
            final Double targetRate = rate.getRates().get(target);
            log.info("{} rate: {}", target, targetRate);
            return amountList.parallelStream()
                    .map(amount -> amount.multiply(BigDecimal.valueOf(targetRate)))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Rate not supported");
        }
    }

}
