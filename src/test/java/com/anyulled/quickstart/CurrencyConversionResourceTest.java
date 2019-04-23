package com.anyulled.quickstart;

import io.quarkus.test.junit.QuarkusTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

@QuarkusTest
@Slf4j
public class CurrencyConversionResourceTest {

    @Test
    @DisplayName("Country resource")
    public void countryEndpoint() {
        given()
                .when().get("/country/spain")
                .then()
                .statusCode(200)
                .body("alpha2Code", equalTo("ES"),
                        "name", equalTo("Spain"),
                        "currencies[0].code", equalTo("EUR"));
    }

    @Test
    @DisplayName("get rate")
    public void rateEndpoint() {
        given()
                .when().get("/rate/gbp")
                .then()
                .statusCode(200)
                .body("base", is("GBP"),
                        "rates.CHF", notNullValue(),
                        "rates.size()", equalTo(33));
    }

    @Test
    @DisplayName("Convert a single amount")
    public void convertAmount() {
        given()
                .when().get("rate/convert/EUR/1000/GBP")
                .then()
                .statusCode(200)
                .body(is(notNullValue()));
    }

    @Test
    @DisplayName("Convert an amount list")
    public void convertAmountList() {
        given()
                .param("amounts", 1000)
                .param("amounts", 6000)
                .param("amounts", 9999.000)
                .when().post("rate/convert/EUR/USD")
                .then()
                .statusCode(200)
                .body(is(notNullValue()))
                .body("[0]", Matchers.greaterThan(1000F))
                .body("[1]", Matchers.greaterThan(5000F))
                .body("[2]", Matchers.greaterThan(10000F))
        ;
    }

    @Test
    @DisplayName("Cache results")
    public void cacheResult() {
        given()
                .when().get("/country/spain")
                .then()
                .statusCode(200)
                .time(greaterThan(1000L));

        given()
                .when().get("/country/spain")
                .then()
                .statusCode(200)
                .time(lessThan(500L));

    }
}