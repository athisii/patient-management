package com.athisii;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author athisii ekhe
 * @version 1.0
 * @since 4/8/25
 */

class AuthIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8443";
    }

    @Test
    void shouldReturnOkWithValidToken() {
        /*
            1. Arrange -- set up the requirement
            2. Act  -- perform action on the setup
            3. Assert -- validate the result
         */
        String loginPayload = """
                {
                 "email": "test@email.com",
                 "password": "password123"
                }
                """;

        Response response = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .body("data", notNullValue())
                .extract()
                .response();

        System.out.println("Generated token: " + response.jsonPath().getString("data"));
    }

    @Test
    void shouldReturnUnauthorizedOnInvalidLogin() {
        String loginPayload = """
                {
                 "email": "invalid_user@email.com",
                 "password": "wrong_password"
                }
                """;
        given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(401);

    }
}
