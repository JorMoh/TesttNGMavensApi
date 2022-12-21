package petStoreApiTests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreApi {
	
	String categoryName;
	int petID;
	
	
  @BeforeTest
  public void setup() {
	  RestAssured.baseURI = "https://petstore.swagger.io/v2";
  }
	
  
  @Test(dependsOnMethods = {"addPet", "updatePet"})
  public void getPetByID() {
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().get("/pet/1234");
	  
		response.then().assertThat().statusCode(200).and().contentType("application/json");	 
		
		int petID= response.body().path("id");
		Assert.assertEquals(petID, 1234);

		String categoryName = response.body().path("category.name");
		Assert.assertEquals(categoryName, "dogs");

		String petName = response.body().path("name");
		Assert.assertEquals(petName, "Max");

		int tagID1 = response.body().path("tags.id[0]");
		Assert.assertEquals(tagID1, 0);

		String tagName2 = response.body().path("tags.name[1]");
		Assert.assertEquals(tagName2, "String2");

		String petStatus = response.body().path("status");
		Assert.assertEquals(petStatus, "available");

		
  }
  

  
  @Test
  public void getPetByStatus() {
	  
//	  "Content-Type" indicates the format of the message body (either request or response).
//
//	  "Accept" in a request indicates the preferred format for the response.
	
	 Response response = RestAssured
	  .given().accept(ContentType.JSON).param("status", "available")
	  .when().get("/pet/findByStatus"); //or "/pet/findByStatus?status=available without using param.
	 
	  response.then().assertThat().statusCode(200).and().contentType("application/json");
  }
  
  
  
  @Test
  public void addPet() {
	  
//	 String requestBody = "{\n"
//	 		+ "  \"id\": 1234,\n"
//	 		+ "  \"category\": {\n"
//	 		+ "    \"id\": 21,\n"
//	 		+ "    \"name\": \"string\"\n"
//	 		+ "  },\n"
//	 		+ "  \"name\": \"Max\",\n"
//	 		+ "  \"photoUrls\": [\n"
//	 		+ "    \"string\"\n"
//	 		+ "  ],\n"
//	 		+ "  \"tags\": [\n"
//	 		+ "    {\n"
//	 		+ "      \"id\": 0,\n"
//	 		+ "      \"name\": \"string\"\n"
//	 		+ "    },\n"
//	 		+ "    {\n"
//	 		+ "        \"id\": 1,\n"
//	 		+ "        \"name\":\"String2\"\n"
//	 		+ "    }\n"
//	 		+ "  ],\n"
//	 		+ "  \"status\": \"available\"\n"
//	 		+ "}";
	 //OR:
	 File requestBody = new File("./src/test/resources/JSonTestData/addPet.json");
			  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .body(requestBody).contentType("application/json")
			  .when().post("/pet");
	  
	  response.then().assertThat().statusCode(200).and().contentType("application/json");
	  petID = response.body().jsonPath().get("id");
	 
  }
  
  
  
  @Test(dependsOnMethods="addPet")
  public void updatePet() {
	  //updating the category name to "dogs".
	  String requestBody = "{\n"
	  		+ "  \"id\": 1234,\n"
	  		+ "  \"category\": {\n"
	  		+ "    \"id\": 21,\n"
	  		+ "    \"name\": \"dogs\"\n"
	  		+ "  },\n"
	  		+ "  \"name\": \"Max\",\n"
	  		+ "  \"photoUrls\": [\n"
	  		+ "    \"string\"\n"
	  		+ "  ],\n"
	  		+ "  \"tags\": [\n"
	  		+ "    {\n"
	  		+ "      \"id\": 0,\n"
	  		+ "      \"name\": \"string\"\n"
	  		+ "    },\n"
	  		+ "    {\n"
	  		+ "        \"id\": 1,\n"
	  		+ "        \"name\":\"String2\"\n"
	  		+ "    }\n"
	  		+ "  ],\n"
	  		+ "  \"status\": \"available\"\n"
	  		+ "}";
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON).contentType("application/json")
			  .when().body(requestBody).put("/pet");
	  response.prettyPrint();
	  response.then().assertThat().statusCode(200).and().contentType("application/json");
	  
	   categoryName= response.body().jsonPath().get("category.name");
	 //Assert.assertEquals(response.body().jsonPath().get("category.name"), "dogs");
	   Assert.assertEquals(categoryName, "dogs");
	   
	   petID = response.body().jsonPath().get("id");
			 
  }
  
  
  
 
  public void deletePet() {
	  
	  	  Response response = RestAssured
			  .given().accept(ContentType.JSON)
			  .when().delete("/pet/"+petID);
	  	 
	  response.then().assertThat().statusCode(200);
	  //verify pet was deleted:
		Assert.assertEquals(response.body().jsonPath().get("message"), String.valueOf(petID));
	  //OR:
		//.body("message", equalTo(String.valueOf(petID)));
	
  }
  
  
  //Negative test case
  
  @Test
	public void invalidIdUpdatePet() {
		String requestBody = "{\n"
				+ "  \"id\": 123123123f,\n"
				+ "  \"category\": {\n"
				+ "    \"id\": 21,\n"
				+ "    \"name\": \"string\"\n"
				+ "  },\n"
				+ "  \"name\": \"Max\",\n"
				+ "  \"photoUrls\": [\n"
				+ "    \"string\"\n"
				+ "  ],\n"
				+ "  \"tags\": [\n"
				+ "    {\n"
				+ "      \"id\": 0,\n"
				+ "      \"name\": \"string\"\n"
				+ "    },\n"
				+ "    {\n"
				+ "        \"id\": 1,\n"
				+ "        \"name\":\"String2\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"status\": \"available\"\n"
				+ "}";
		
		Response catResponse = RestAssured
		.given().accept(ContentType.JSON).contentType("application/json").body(requestBody)
		.when().put("/pet");
		
		catResponse.then().statusCode(400).and().contentType("application/json");
		catResponse.prettyPrint();
		Assert.assertEquals(catResponse.body().jsonPath().get("message"), "bad input");
		
	}
  
  
  @AfterTest
  public void cleanup() {
	  deletePet();
  }
}
