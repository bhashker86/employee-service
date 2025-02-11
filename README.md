# employee-service

Implement a Spring based REST Services application which has 
One endpoint that allows you to lookup salary of a given employee (employeeName as input)
For this you can use an internal in-memory Hashmap which has key as String, value as Double where key is the name of the employee and salary is the Double value
You should handle happy path scenario and a few negative scenarios like employee not found or employee is found in the map but there is null value as salary , an incorrect input value of employee name (preferably i want to see exception handling)
Implement unit test cases for the classes you implement
Implement an integration test case (using RestTemplate or some kind of HttpClient) and to test the above endPoint
