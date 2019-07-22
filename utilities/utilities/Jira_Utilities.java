package utilities;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utilities.RestAPI_Utilities;

public class Jira_Utilities {
	
	private static String issueID;
	private static String commentID;

	public static String getSessionID() {

		Response res = given().header("Content-Type", "application/json")
				.body("{ \"username\": \"erdemdenizli\", \"password\": \"password\" }").when()
				.post("/rest/auth/1/session").then().statusCode(200).extract().response();

		JsonPath jp = RestAPI_Utilities.rawToJSON(res);
		return jp.get("session.value");

	}
	
	public static void createBug() {
				
		Response res = 
		given().header("Content-Type", "application/json").
		header("cookie", "JSESSIONID="+getSessionID()).
		body("{\n" + 
				"	\"fields\": {\n" + 
				"		\"project\": {\n" + 
				"			\"key\": \"REST\"\n" + 
				"		},\n" + 
				"		\"summary\": \"This is the summary, testing creating a bug\",\n" + 
				"		\"description\": \"This is the description, creating a bug\",\n" + 
				"		\"issuetype\": {\n" + 
				"			\"name\": \"Bug\"\n" + 
				"		}\n" + 
				"	}\n" + 
				"}").
		when().post("/rest/api/2/issue").
		then().statusCode(201).
		extract().response();
		
		JsonPath jp = RestAPI_Utilities.rawToJSON(res);
		issueID = jp.get("id");
		System.out.println("Issue ID: "+issueID);
		
	}
	
	public static void postComment() {
				
		Response res = 
				given().header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+getSessionID()).
				body("{\n" + 
						"	\"body\": \"This comment is ADDED by automation\",\n" + 
						"	\"visibility\": {\n" + 
						"		\"type\": \"role\",\n" + 
						"		\"value\": \"Administrators\"\n" + 
						"	}\n" + 
						"}").
				when().post("/rest/api/2/issue/"+issueID+"/comment").
				then().statusCode(201).
				extract().response();
		JsonPath jp = RestAPI_Utilities.rawToJSON(res);
		commentID = jp.get("id");
		System.out.println("Comment ID: "+commentID);
		
	}
	
	public static void updateComment() {
				
		Response res = 
				given().header("Content-Type", "application/json").
				header("cookie", "JSESSIONID="+getSessionID()).
				body("{\n" + 
						"	\"body\": \"This comment is UPDATED by automation\",\n" + 
						"	\"visibility\": {\n" + 
						"		\"type\": \"role\",\n" + 
						"		\"value\": \"Administrators\"\n" + 
						"	}\n" + 
						"}").
				when().put("/rest/api/2/issue/"+issueID+"/comment/"+commentID).
				then().statusCode(200).
				extract().response();
		JsonPath jp = RestAPI_Utilities.rawToJSON(res);
		commentID = jp.get("id");
		System.out.println("Comment ID: "+commentID);
		
	}

}
