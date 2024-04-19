package base_urls;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C12_PostRequestMapTestData extends JsonPlaceHolderBaseUrl {
/*
     Given
       1) https://jsonplaceholder.typicode.com/todos
       2)  {
             "userId": 55,
             "title": "Tidy your room",
             "completed": false
          }
    When
        I send POST Request to the Url

    Then
        Status code is 201
    And
        response body is like {
                                "userId": 55,
                                "title": "Tidy your room",
                                "completed": false,
                                "id": 201
                                }
*/

    @Test
    public void postRequestTest() {

        //Set the Url
        spec.pathParams("first", "todos");

        //Set the expected data(Payload) --> Prepare it as Map
        Map<String, Object> expectedData = JsonPlaceHolderTestData.expectedDataMap(55,"Tidy your room", false);

        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");

        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(201)
                .body("userId", equalTo(expectedData.get("userId")), //By map payload, we can get specific data from body. This is more dynamic.
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));

    }

}
