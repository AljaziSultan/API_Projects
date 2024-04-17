import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Assignment02 {

 /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           "email" is "janet.weaver@reqres.in",
       And
           "first_name" is "Janet"
       And
           "last_name" is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */
/*
    Steps to follow in API Testing:
    1. Set the URL
    2. Set the expected data
    3. Send the request and get the response
    4. Do Assertion
*/

    @Test
    public void assertBodyMethod() {

        //1. Set the url
//           https://reqres.in/api/users/2
        String url = "https://reqres.in/api/users/2";

        //2. Set the expected data --> We will do this in post and put requests

        //3. Send the request and get the response
//        User sends a GET request to the URL
        Response response = given().get(url);//given method is used for pre-request
        response.prettyPrint();

        //4. Do Assertion
//        HTTP Status Code should be 200
//        Response format should be “application/json”
//        “title” is “et itaque necessitatibus maxime molestiae qui quas velit”
//        “completed” is false
//        “userId”
        //2nd Way:
        response
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("email", equalTo("janet.weaver@reqres.in"),
                        "first_name", equalTo("janet"),
                        "last_name",equalTo("Weaver"),
                        "text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));//To do multiple assertion in one single body method works as SOFT ASSERTION

        //Hard assertion stops the execution in the first failure but soft assertion do not.

    }

}
