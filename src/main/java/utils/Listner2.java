package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listner2 implements ITestListener {
	
public void onStart(ITestContext context) {
		
		
		System.out.println("This is onStart method" +context.getOutputDirectory());
		
	}
    
}
