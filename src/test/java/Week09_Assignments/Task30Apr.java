package Week09_Assignments;

import io.restassured.builder.RequestSpecBuilder;
import org.junit.Before;

public class Task30Apr {





    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).addHeader("X-RapidAPI-Key","193fe7b932mshd018473dd984abbp13ba61jsn7f8835a09401").
                addHeader("X-RapidAPI-Host","community-zippopotamus.p.rapidapi.com").setBaseUri("https://community-zippopotamus.p.rapidapi.com").build();
    }
}
