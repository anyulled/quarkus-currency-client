package com.anyulled.quickstart.utils;

import com.anyulled.quickstart.model.Country;
import com.anyulled.quickstart.model.Rate;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.temporal.ChronoUnit;

@Data
@UtilityClass
public class CacheHelper {

    private CacheManager cacheManager;

    private Cache<String, Country> countryCache;

    private Cache<String, Rate> rateCache;

    private void initialize() {
        if (cacheManager == null) {
            cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
            cacheManager.init();

            CacheConfiguration<String, Country> countryCacheConfiguration = CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(String.class, Country.class, ResourcePoolsBuilder.heap(10))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(java.time.Duration.of(30, ChronoUnit.DAYS)))
                    .build();
            CacheConfiguration<String, Rate> rateCacheConfiguration = CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(String.class, Rate.class, ResourcePoolsBuilder.heap(10))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(java.time.Duration.of(24, ChronoUnit.HOURS)))
                    .build();

            countryCache = cacheManager.createCache("country", countryCacheConfiguration);
            rateCache = cacheManager.createCache("rate", rateCacheConfiguration);
        }
    }

    public Cache<String, Rate> getRateFromCacheManager() {
        initialize();
        return cacheManager.getCache("rate", String.class, Rate.class);
    }

    public Cache<String, Country> getCountryFromCacheManager() {
        initialize();
        return cacheManager.getCache("country", String.class, Country.class);
    }
}
