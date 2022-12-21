package petStoreApiTests;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import okhttp3.RequestBody;

public class ChainValidation {
	
	

  @Test
  public void chainVaildate() {
	  String requestBody = "{\n"
	  		+ "  \"id\": 1234,\n"
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
	  
	  Response response = RestAssured
			  .given().accept(ContentType.JSON).contentType("application/json")
			  .body(requestBody)
			  .when().post("https://petstore.swagger.io/v2/pet/");
	  
	  response.prettyPrint();
	  
	  response.then()
	  .assertThat()
	  .statusCode(200).and().contentType("application/json")
	  .body("id", equalTo(1234))
	  .body("category.id", equalTo(21))
	  .body("category.name", equalTo("string"))
	  .body("name", equalTo("Max"))
	  .body("tags.id[0]", equalTo(0))
	  .body("tags.id[1]", equalTo(1))
	  .body("tags.name[0]", equalTo("string"))
	  .body("tags.name[1]", equalTo("String2"))
	  .body("status", equalTo("available"));
	 
	  

  }
}
