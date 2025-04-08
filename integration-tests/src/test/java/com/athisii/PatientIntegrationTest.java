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

class PatientIntegrationTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://localhost:8443";
    }

    @Test
    void shouldReturnPatientsWithValidToken() {
        String loginPayload = """
                {
                 "email": "test@email.com",
                 "password": "password123"
                }
                """;
        String token = given()
                .contentType("application/json")
                .body(loginPayload)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getString("data");

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(200)
                .body("data", notNullValue())
                .extract()
                .response();
        System.out.println("Patients: " + response.jsonPath().get("data"));

    }
}
