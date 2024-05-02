package Week10_Assignments;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

public class Assignment14 {

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770
 */
    public static class Employee {
        private String id;
        private String employeeName;
        private int employeeSalary;
        private int employeeAge;
        private String profileImage;

        public String getEmployeeName() {
            return employeeName;
        }
        public int getEmployeeAge() {
            return employeeAge;
        }
        public int getEmployeeSalary() {
            return employeeSalary;
        }
    }
    private final String URL = "https://dummy.restapiexample.com/api/v1/employees";

    @Test
    public void testEmployees() {
        Response response = RestAssured.given().contentType(ContentType.JSON).get(URL);
        response.then().statusCode(200);
        //LIST EMPLOYEES
        List<Employee> employees = Arrays.asList(response.as(Employee[].class));
        //TOTAL EMPLOYEES
        assertThat(employees.size(), equalTo(24));
        //CHECK NAMES AVAILABLE
        List<String> employeeNames = employees.stream().map(Employee::getEmployeeName).collect(Collectors.toList());
        assertThat(employeeNames, hasItems("Tiger Nixon", "Garrett Winters"));
        //FIND GREATEST AGE
        int GreatAge = employees.stream().mapToInt(Employee::getEmployeeAge).max().getAsInt();
        assertThat(GreatAge, equalTo(66));
        //FIND LOWEST AGE
        Employee theYoungest = employees.stream().min(Comparator.comparingInt(Employee::getEmployeeAge)).get();
        assertThat(theYoungest.getEmployeeName(), equalTo("Tatyana Fitzpatrick"));
        //TOTAL SALARY
        int totalSalary = employees.stream().mapToInt(Employee::getEmployeeSalary).sum();
        assertThat(totalSalary, equalTo(6644770));
    }
}
