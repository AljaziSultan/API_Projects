package Week09_Assignments;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
///*
//Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
//*/
public class Assignment09W09 {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void PayloadCreateUser() {
        Map<String, Object> Payload = new HashMap<>();
        Payload.put("id", 1);
        Payload.put("username", "Jasmine");
        Payload.put("firstName", "Jaz");
        Payload.put("lastName", "Mine");
        Payload.put("email", "jasmine@gmail.com");
        Payload.put("password", "1234");
        Payload.put("phone", "0555555");
        Payload.put("userStatus", 0);


        // Send POST request with user data
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(Payload)
                .post("/users");
    }

}

