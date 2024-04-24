package Week09_Assignments;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.testng.Assert;

//Using the https://petstore.swagger.io/ document,
// write an automation test that finds the number of "pets"
// with the status "available"
// and asserts that there are more than 100.
public class Assignment10W09 {

        @Test
        public void testAvailablePetCount() throws Exception {
            Response response = RestAssured.get("https://petstore.swagger.io/v2");
            int statusCode = response.getStatusCode();
            System.out.println("statusCode = " + statusCode);//200

            String contentType = response. contentType();
            System.out.println("contentType = " + contentType);

            int availableCount = response.jsonPath().getList("id").size();
            Assert.assertTrue(availableCount > 100, "Number of available pets is not more than 100");
    }
}
