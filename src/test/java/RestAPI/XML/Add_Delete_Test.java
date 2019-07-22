package RestAPI.XML;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utilities.RestAPI_Utilities;

public class Add_Delete_Test {
	
	Properties prop = new Properties();
	FileInputStream fis;
	
	@BeforeMethod
	public void loadData() throws IOException {
		fis = new FileInputStream("/Users/erdemdenizli/eclipse-workspace/RestAPI/src/test/java/RestAPI/XML/data.properties");
		prop.load(fis);
	}
	
	@Test
	public void addDeleteData() throws IOException {
		
		String postdata = RestAPI_Utilities.XMLtoString(prop.getProperty("postdata_path"));
		
		RestAssured.baseURI = prop.getProperty("HOST");

//		given().queryParam("key", "qaclick123")
//				.body("{\n" + "\n" + "    \"location\":{\n" + "\n" + "        \"lat\" : -38.383494,\n" + "\n"
//						+ "        \"lng\" : 33.427362\n" + "\n" + "    },\n" + "\n" + "    \"accuracy\":50,\n" + "\n"
//						+ "    \"name\":\"Frontline house\",\n" + "\n"
//						+ "    \"phone_number\":\"(+91) 983 893 3937\",\n" + "\n"
//						+ "    \"address\" : \"29, side layout, cohen 09\",\n" + "\n"
//						+ "    \"types\": [\"shoe park\",\"shop\"],\n" + "\n"
//						+ "    \"website\" : \"http://google.com\",\n" + "\n" + "    \"language\" : \"French-IN\"\n"
//						+ "\n" + "}")
//				.when().post(prop.getProperty("resource_add")).then().assertThat().statusCode(200).and()
//				.contentType(ContentType.JSON).and().body("status", equalTo("OK"));

		Response res = given().queryParam("key", "qaclick123")
				.body(postdata)
				.when().post(prop.getProperty("resource_add")).then().assertThat().statusCode(200).and()
				.contentType(ContentType.XML).extract().response();
		
		XmlPath x = RestAPI_Utilities.rawToXML(res);
		
		//JsonPath jp = new JsonPath(response);
		
		String placeID = x.get("response.place_id");
		
		System.out.println(placeID);
		
		given().
		queryParam("key", "qaclick123").
		body("{\n" + 
				"	\n" + 
				"	\"place_id\": " + placeID + 
				"	\n" + 
				"}").when().post(prop.getProperty("resource_delete")).then().assertThat().statusCode(200);

	}

}
