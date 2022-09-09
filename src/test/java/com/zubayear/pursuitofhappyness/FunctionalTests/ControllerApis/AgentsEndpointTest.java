package com.zubayear.pursuitofhappyness.FunctionalTests.ControllerApis;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class AgentsEndpointTest {
    @Test
    public void createAgentEndpoint() {
        String endpoint = "http://localhost:8080/agents";
        String name = /*Faker.instance().name().fullName()*/ "";
        String body = "{\"name\": \"" + name + "\"}";
        given().header("Content-Type", "application/json").body(body)
                .when().post(endpoint)
                .then().log().all()
                .assertThat()
                .statusCode(201);
    }
}
