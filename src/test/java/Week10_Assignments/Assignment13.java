package Week10_Assignments;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;

public class Assignment13 {



    //Write an automation test that will create a 'user' then read, update and delete the
    // created user using the "https://petstore.swagger.io/" document. (Create a classes for each request.)

    private static String url = "https://petstore.swagger.io/v2";
    private static String ID;
    private static RequestSpecBuilder requestSpecBuilder;

    @BeforeAll
    public static void setUp() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBasePath(url);
    }

    @Test
    public void create() {
        // User data
        Map<String, Object> user = new HashMap<>();
        user.put("username", "Username" + System.currentTimeMillis());
        user.put("firstName", "User");
        user.put("lastName", "Testing");
        user.put("email", "test_user@example.com");
        user.put("password", "test_password");
        user.put("phone", "1234567890");

        // Create user request
        Response createResponse = RestAssured.given(requestSpecBuilder.build())
                .contentType(ContentType.JSON)
                 .body(user)
                .post("/user");

        // Assert successful creation
        createResponse.then()
                .statusCode(200)
                .body("username", equalTo(user.get("username")))
                .body("firstName", equalTo(user.get("firstName")))
                .body("lastName", equalTo(user.get("lastName")))
                .body("email", equalTo(user.get("email")));

        // Extract user ID for further requests
        ID = createResponse.then().extract().path("id");
    }
    private Map<String, Object> getUserFromResponse(Response response) {
        return response.as(Map.class);
    }
    @Test(dependsOnMethods={"create"})
    public void read() {
        // Read user request
        Response readResponse = RestAssured.given(requestSpecBuilder.build())
                .pathParam("userId", ID)
                .get("/user/{userId}");

        // Assert successful read
        readResponse.then()
                .statusCode(200)
                .body("id", equalTo(Integer.parseInt(ID)))
                .body("username", equalTo(getUserFromResponse(readResponse).get("username")));
    }
 @Test(dependsOnMethods={"create"})

    public void update() {
        // User data for update
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("firstName", "Updated First Name");
        updateData.put("lastName", "Updated Last Name");

        // Update user request
        Response updateResponse = RestAssured.given(requestSpecBuilder.build())
                .pathParam("userId", ID)
                .contentType(ContentType.JSON)
                .body(updateData)
                .put("/user/{userId}");

        // Assert successful update
        updateResponse.then()
                .statusCode(200)
                .body("firstName", equalTo(updateData.get("firstName")))
                .body("lastName", equalTo(updateData.get("lastName")));
    }
    @Test(dependsOnMethods={"create"})
    public void deleteUSER() {
        // Delete user request
        Response deleteResponse = RestAssured.given(requestSpecBuilder.build())
                .pathParam("userId", ID)
                .delete("/user/{userId}");

        // Assert successful deletion (usually 204 No Content)
        deleteResponse.then().statusCode(204);
    }
}
