package Week08_Assignments;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class Assignment07W08 {
      /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then

            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */
      @Test
      public void printingConditionalValues() {
          JsonPath response = (JsonPath) RestAssured.when()
//         https://reqres.in/api/unknown/
                  .get("https://reqres.in/api/unknown")
                  .then()
//          1)Status code is 200
                  .statusCode(200);
//          2)Print all pantone_values
          System.out.println("Pantone Values:");
          response.getList("data.pantone_value").forEach(System.out::println);
//          3)Print all ids greater than 3 on the console
          System.out.println("IDs greater than 3:");
          response.getList("data.findAll { it.id > 3 }.id").forEach(System.out::println);
//           Assert that there are 3 ids greater than 3
          int ids_3 = response.getInt("data.findAll { it.id > 3 }.size()");
          assertThat(ids_3, equalTo(3));
//          4)Print all names whose ids are less than 3 on the console
          System.out.println("Names with IDs less than 3:");
          response.getList("data.findAll { it.id < 3 }.name").forEach(System.out::println);
//         Assert that the number of names whose ids are less than 3 is 2
          int names_3 = response.getInt("data.findAll { it.id < 3 }.size()");
          assertThat(names_3, equalTo(2));

      }
}
