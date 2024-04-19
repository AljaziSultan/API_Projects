package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BookerBaseUrl {
    // We avoid repetitive tasks when sending requests with the RequestSpecification object.
    // For example: repetitive tasks such as base URL and headers.

    protected RequestSpecification spec;//This is null in this line, setUp() method should run before using this.

    @BeforeMethod //Runs before each @Test
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api/users/23")
                .build();

    }

}
