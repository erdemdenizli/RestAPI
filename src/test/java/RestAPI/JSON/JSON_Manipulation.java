package RestAPI.JSON;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.RestAPI_Utilities;

public class JSON_Manipulation {

	@Test
	public void getNames() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		Response res = 
		given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "500").
		param("key", "AIzaSyBMyPW-8WdN4lX1bzRDHjrXgukdNPWTMhw").
		log().all().
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		and().body("results[0].name", equalTo("Sydney")).
		extract().response();
		
		JsonPath jp = RestAPI_Utilities.rawToJSON(res);
		
		int resultsSize = jp.get("results.size()");
		
		System.out.println(resultsSize);
		
		for(int i=0; i<resultsSize; i++) {
			String s = jp.get("results["+i+"].name");
			System.out.println(s);
		}
		
	}

}
