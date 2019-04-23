package com.anyulled.quickstart.service;

import com.anyulled.quickstart.model.Rate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public interface RateService {

    Rate getRates(String base);

    BigDecimal convert(@NotNull String base, @NotNull BigDecimal amount, @NotNull String target);

    List<BigDecimal> convert(@NotNull String base, @NotNull List<BigDecimal> amount, @NotNull String target);
}
