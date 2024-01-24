package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JsonTestDataFetcher {
	private static final String JSON_FILE_PATH = "D:/Users/AKSHAY/new-eclipse-workspace/TestAkshaySeleniumProject/src/main/resources/testdata.json";

	public static Object[][] fetchTestData() {
		Object[][] testData = null ;
		try {
// Read the JSON file
			FileReader fileReader = new FileReader(JSON_FILE_PATH);
			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray =  (JSONArray) jsonParser.parse(fileReader);
			
			
// Initialize the test data array with the size of the JSON array 
			testData = new Object[jsonArray.size()][2];
			
			System.out.println("priting test data variable======"+testData);
			System.out.println("jsonarray ======"+jsonArray);
			System.out.println("jsonarray ======"+jsonArray.size());
			
			
			
// Iterate through the JSON array and extract the test data
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String firstName = (String) jsonObject.get("firstName");
				String lastName = (String) jsonObject.get("lastName");
				testData[i][0] = firstName;
				testData[i][1] = lastName;
				System.out.println(firstName);
				System.out.println(lastName);
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return testData;
	}
}