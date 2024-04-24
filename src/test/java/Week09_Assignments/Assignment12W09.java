package Week09_Assignments;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
//import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Assignment12W09 {

        private static final String BASE_URL = "https://petstore.swagger.io/v2";
        private static final ObjectMapper mapper = new ObjectMapper();



        public static class Pet {
            private long id;
            private String name;
            private Category category;
            private List<String> tags;
            private String status;

        }

        public static class Category {
            private long id;
            private String name;

            public Category(int i, String dogs) {
            }

            // Getters and setters (omitted for brevity)
        }

        @Test
        public void testPetCrud() throws Exception {
            // 1. Create a new Pet object
            Pet newPet = new Pet();
            newPet.setName("Test Pet");
            newPet.setCategory(new Category(1, "Dogs")); // Replace with desired category ID
            newPet.setStatus("available");

            // Send POST request to create the pet
            RestTemplate restTemplate = new RestTemplate();
            String createResponse = restTemplate.postForObject(BASE_URL + "/pet", newPet, String.class);
            Pet createdPet = mapper.readValue(createResponse, Pet.class);

            // 2. Read the created pet
            String readResponse = restTemplate.getForObject(BASE_URL + "/pet/" + createdPet.getId(), String.class);
            Pet readPet = mapper.readValue(readResponse, Pet.class);

            // Assert that read pet details match the created pet
            assertPetDetails(createdPet, readPet);

            // 3. Update the pet's name
            createdPet.setName("Updated Test Pet");
            String updateResponse = restTemplate.putForObject(BASE_URL + "/pet", createdPet, String.class);

            // 4. Read the updated pet
            readResponse = restTemplate.getForObject(BASE_URL + "/pet/" + createdPet.getId(), String.class);
            readPet = mapper.readValue(readResponse, Pet.class);

            // Assert that read pet details match the updated pet
            assertPetDetails(createdPet, readPet);

            // 5. Delete the pet
            restTemplate.delete(BASE_URL + "/pet/" + createdPet.getId());

            // 6. Try to read the deleted pet (should return 404)
            try {
                restTemplate.getForObject(BASE_URL + "/pet/" + createdPet.getId(), String.class);
                org.junit.Assert.fail("Pet should be deleted!");
            } catch (org.springframework.web.client.ResourceAccessException e) {
                // Expected exception for deleted pet
            }
        }

    private void assertPetDetails(Pet expected, Pet actual) {
        org.junit.Assert.assertEquals(expected.getId(), actual.getId());
        org.junit.Assert.assertEquals(expected.getName(), actual.getName());
        org.junit.Assert.assertEquals(expected.getCategory().getId(), actual.getCategory().getId());
        org.junit.Assert.assertEquals(expected.getCategory().getName(), actual.getCategory().getName());
        org.junit.Assert.assertEquals(expected.getStatus(), actual.getStatus());
    }
    }

}
