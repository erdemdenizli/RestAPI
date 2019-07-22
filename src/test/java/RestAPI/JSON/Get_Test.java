package RestAPI.JSON;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Get_Test {
	
	@Test
	public void getData() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		given().
		param("location", "-33.8670522,151.1957362").
		param("radius", "500").
		param("key", "AIzaSyBMyPW-8WdN4lX1bzRDHjrXgukdNPWTMhw").
		when().
		get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		and().body("results[0].name", equalTo("Sydney"));
		System.out.println("****");
		
		given().
				param("location", "-33.8670522,151.1957362").
				param("radius", "500").
				param("key", "AIzaSyBMyPW-8WdN4lX1bzRDHjrXgukdNPWTMhw").
				when().
				get("/maps/api/place/nearbysearch/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
				//and().body("results[0].geometry.location.lat", equalTo("-33.86882"));
		System.out.println("****");
		
	}

}
