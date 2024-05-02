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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
public class Assignment16 {
    private int ID;
    //Write an automation test that will add a 'contact' then read, update and delete the created contact
    // then negative assert the deleted contact
    // using the "https://documenter.getpostman.com/view/4012288/TzK2bEa8" document.
    @BeforeClass
    public void setup() {
        // Replace with your actual base URL from the documentation
        RestAssured.baseURI = "https://documenter.getpostman.com/view/4012288/TzK2bEa8";
    }

    @Test
    public void createContact() {
        // Create a new contact
        Map<String, Object> newContact = new HashMap<>();
        newContact.put("name", "Test Contact");
        newContact.put("email", "test@gmail.com");

        Response createResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newContact)
                .post("/contacts");

        createResponse.then()
                .statusCode(201)
                .extract().body().as(Map.class);

        ID = createResponse.jsonPath().getInt("id");
//     _____________________________________________________________
        // Read the created contact
        Response readResponse = RestAssured
                .given()
                .get("/contacts/" + ID);

        readResponse.then()
                .statusCode(200)
                .body("name", equalTo("Test Contact"))
                .body("email", equalTo("test@example.com"));
//     _____________________________________________________________
        // Update the contact
        newContact.put("name", "Updated Test Contact");
        Response updateResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(newContact)
                .put("/contacts/" + ID);

        updateResponse.then()
                .statusCode(200)
                .body("name", equalTo("Updated Test Contact"));
//     _____________________________________________________________
        // Delete the contact
        Response deleteResponse = RestAssured
                .given()
                .delete("/contacts/" + ID);

        deleteResponse.then()
                .statusCode(204);

        // Negative assertion - Ensure the deleted contact cannot be read
        Response noResponse = RestAssured
                .given()
                .get("/contacts/" + ID);

        noResponse.then()
                .statusCode(404)
                .body("message", not(equalTo("")));
}}


