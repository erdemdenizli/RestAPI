package end2end;

import io.restassured.RestAssured;
import utilities.Jira_Utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JiraAPI {
	
	Properties prop = new Properties();
	FileInputStream fis;
	String issueID;
	String commentID;
	
	private static Logger log = LogManager.getLogger(JiraAPI.class.getName());
	
	@BeforeMethod
	public void loadData() throws IOException {
		fis = new FileInputStream("/Users/erdemdenizli/eclipse-workspace/RestAPI/src/test/java/RestAPI/JSON/data.properties");
		prop.load(fis);
	}
	
	@Test
	public void createBug() {
		
		RestAssured.baseURI=prop.getProperty("HOST_JIRA");
		
		Jira_Utilities.createBug();
		log.info("Bug created");
		
	}
	@Test
	public void postComment() {
		
		RestAssured.baseURI=prop.getProperty("HOST_JIRA");
		
		Jira_Utilities.postComment();
		log.info("Comment posted");

		
	}
	@Test
	public void updateComment() {
		
		RestAssured.baseURI=prop.getProperty("HOST_JIRA");
		
		Jira_Utilities.updateComment();
		log.info("Comment updated");

		
	}

}
