package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class RestAPI_Utilities {

	public static String XMLtoString(String path) throws IOException {

		String postdata = new String(Files.readAllBytes(
				Paths.get(path)));

		return postdata;

	}
	public static String JSONtoString(String path) throws IOException {
		
		String postdata = new String(Files.readAllBytes(
				Paths.get(path)));
		
		return postdata;
		
	}

	public static XmlPath rawToXML(Response res) {

		String response = res.asString();
		
		System.out.println(response);

		XmlPath xml = new XmlPath(response);
		
		return xml;

	}
	
	public static JsonPath rawToJSON(Response res) {
		
		String response = res.asString();
		
		System.out.println(response);
		
		JsonPath jp = new JsonPath(response);
		
		return jp;
		
	}

}
