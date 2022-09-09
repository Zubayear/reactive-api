package com.zubayear.pursuitofhappyness.FunctionalTests.ControllerApis;

import com.github.javafaker.Faker;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AgentsControllerTest {
    @Test
    public void createAgent() {
        String endpoint = "http://localhost:8080/api/agents";
        String name = Faker.instance().name().fullName();
        String body = "{\"name\": \"" + name + "\"}";
        // given - pre req (like headers, auth token)
        // when - describe the action to take
        // then - describe the expected result
        ValidatableResponse response =
                given().header(new Header("Content-Type", "application/json")).body(body)
                .when().post(endpoint)
                .then();
        response.log().all().assertThat()
                .statusCode(201)
                .body("name", equalTo(name));
    }

    @Test
    public void getAgent() {
        String endpoint = "http://localhost:8080/api/agents/1";
        ValidatableResponse response = given().when().get(endpoint).then();
        response.log().all();
        response.statusCode(200);
        response.body("name", equalTo("Mr. Kristyn Weber"));
    }

    @Test
    public void deleteAgent() {
        String endpoint = "http://localhost:8080/api/agents/8";
        ValidatableResponse response = given().when().delete(endpoint).then();
        response.log().all();
        response.statusCode(204);
    }
}
