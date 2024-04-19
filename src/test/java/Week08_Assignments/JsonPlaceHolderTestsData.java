package Week08_Assignments;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestsData {

    // With this method, we can create test data in the test class without creating a new map.

    public static Map<String, Object> expectedDataMap(String Name, String Job) {

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("name",  Name);
        expectedData.put("job", Job);


        return expectedData;

    }


}
