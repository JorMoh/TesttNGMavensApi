package petStoreApiTests;

import java.io.File;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class Homework {
	int petID;
	
	
  @BeforeMethod
  public void setup() {
	  RestAssured.baseURI = "https://petstore.swagger.io/v2";
  }
	
  @Test
  public void testCase1() {
	  
//	  Perform a POST request to create a pet with id 3465xxx (change the xxx to numbers).
//	  Give the pet a name booboo in the request body.
//	  Status is available. 
 
	 File requestBody = new File("./src/test/resources/JSonTestData/homeworkPost.json");
	 
	 Response response = RestAssured
	 .given().accept(ContentType.JSON).contentType("application/json")
	 .when().body(requestBody).post("/pet");
	 
//	  Verify the status code is 200 
//	  Verify the content type is application.json
	 response.then()
	 .assertThat().statusCode(200)
	 .and().contentType("application/json");
	 
	 petID = response.body().jsonPath().get("id");
  }
  
  
  @Test(dependsOnMethods="testCase1")
  public void testCase2() {
//	  Perform a GET request to find a pet with id 3465xxx (xxx your number from above post request)
	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().get("/pet/"+petID);
	  
//	  Verify the status code is 200 
//	  Verify the content type is application.json
//	  Verify the pet name is booboo status is available 
	  response.then()
	  .assertThat().statusCode(200)
	  .and().contentType("application/json")
	  .and().body("name", equalTo("booboo"))
	  .and().body("status", equalTo("available"));
	  
  }
  
  @Test
  public void testCase3() {
//	  Perform a GET request to find a pet with ID 7867864

	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().get("/pet/7867864");
//	  Verify the status code is 404 and content type is application.json
//	  Verify message is Pet not found
	  response.then()
	  .assertThat().statusCode(404)
	  .and().contentType("application/json")
	  .and().body("message", equalTo("Pet not found"));

  }
  
}
