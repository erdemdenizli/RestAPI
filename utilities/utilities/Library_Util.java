package utilities;

public class Library_Util {
	
	public static String addBook(String ISBN, String aisle) {
		return "{\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\n" + 
				"\"isbn\":\"" +ISBN+ "\",\n" + 
				"\"aisle\":\"" +aisle+ "\",\n" + 
				"\"author\":\"John foe\"\n" + 
				"}";
	}

}
