package Week10_Assignments;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static Week10_Assignments.Assignment13.requestSpecBuilder;
import static org.hamcrest.Matchers.equalTo;
public class Assignment15
{
    //Write an automation test that will create a 'user' then read,
    //update and delete the created user using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.
    private int ID;
    private static String URL = "https://your-api-base-url";
    @BeforeClass
    public static void setUp() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(URL);
    }
    private Map<String, Object> getUserFromResponse(Response response) {
        return response.as(Map.class);
    }
    @Test
    public void create() {
        // User data
        String name = "TESTER";
        String email = "tester@gmail.com";

        // Create user request
        Response createResponse = RestAssured.given(requestSpecBuilder.build())
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + name + "\", \"email\": \"" + email + "\"}")
                .post("/users");

        // Assert successful creation
        createResponse.then().statusCode(200)
                .body("name", equalTo(name)).body("email", equalTo(email));
        // Extract user ID for further requests
        ID = createResponse.then().extract().path("id");
//        _________________________________________________________________

        // Read user request
        Response readResponse = RestAssured.given()
                .get("/users/" + ID);
        // Assert successful read
        readResponse.then()
                .statusCode(200)
                .body("id", CoreMatchers.equalTo(Integer.parseInt(String.valueOf(ID))))
                .body("username", CoreMatchers.equalTo(getUserFromResponse(readResponse).get("username")));
//        _________________________________________________________________
        // User data for update
        String updatedName = "Jasmine";

        // Update user request
        Response updateResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + updatedName + "\"}")
                .patch("/users/" + ID);

        // Assert successful update
        updateResponse.then()
                .statusCode(200)
                .body("name", CoreMatchers.equalTo(updatedName));
//        _________________________________________________________________

        // Delete user request
        Response deleteResponse = RestAssured.given()
                .delete("/users/" + ID);

        // Assert successful deletion (usually 204 No Content)
        deleteResponse.then().statusCode(204);
    }
}
