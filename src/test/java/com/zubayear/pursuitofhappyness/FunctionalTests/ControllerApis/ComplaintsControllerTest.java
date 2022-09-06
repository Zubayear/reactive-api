package com.zubayear.pursuitofhappyness.FunctionalTests.ControllerApis;

import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ComplaintsControllerTest {
    @Test
    public void createComplaint() {
        String endpoint = "http://localhost:8080/api/agents/1/complaints";
        String body = "{\"sla\": 12, \"description\": \"Unwillingly activated voice bundle.\"}";
        // given - pre req (like headers, auth token)
        // when - describe the action to take
        // then - describe the expected result
        ValidatableResponse response =
                given().header(new Header("Content-Type", "application/json")).body(body)
                .when().post(endpoint)
                .then();
        response.log().all();
        response.statusCode(201);
        response.body("sla", equalTo(24));
    }

    @Test
    public void getComplaint() {
        String endpoint = "http://localhost:8080/api/agents/1/complaints";
        ValidatableResponse response = given().when().get(endpoint).then();
        response.log().body().assertThat()
                .statusCode(200)
//                .body("[]", everyItem(notNullValue()))
                .body("[1].sla", equalTo(12));
    }

    @Test
    public void deleteAgent() {
        String endpoint = "http://localhost:8080/api/agents/1";
        ValidatableResponse response = given().when().delete(endpoint).then();
        response.log().all();
        response.statusCode(204);
    }
}
