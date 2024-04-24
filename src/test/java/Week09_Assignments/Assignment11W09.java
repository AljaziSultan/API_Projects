package Week09_Assignments;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;

import java.util.List;

import static io.restassured.RestAssured.given;

/*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12

    Note: Print using JsonPath: response.jsonPath().prettyPrint();

*/
public class Assignment11W09 {


    @Test
    public void testNumberOfWomenUserType() throws Exception {
        Response response = RestAssured.get("https://automationexercise.com/api/productsList");
         response.jsonPath().prettyPrint();
        List<String> userTypes = response.jsonPath().get("$.products[*].category.usertype.usertype");
        long womenCount = userTypes.stream().filter(type -> type.equals("Women")).count();
        Assert.assertEquals(womenCount ,12);
    }
}
