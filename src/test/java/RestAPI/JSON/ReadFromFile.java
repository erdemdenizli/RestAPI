package RestAPI.JSON;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.Library_Util;
import utilities.RestAPI_Utilities;

public class ReadFromFile {

	
	@Test
	public void addBook() throws IOException {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		Response res = 
		given().
		header("Content-Type", "application/json").
		body(RestAPI_Utilities.JSONtoString("/Users/erdemdenizli/eclipse-workspace/RestAPI/src/test/java/utilities/AddbookDetails.json")).
		when().
		get("Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath jp = RestAPI_Utilities.rawToJSON(res);
		
		String id = jp.get("ID");
		
		System.out.println(id);
		
	}


}
