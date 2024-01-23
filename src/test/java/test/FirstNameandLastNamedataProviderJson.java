package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.JsonTestDataFetcher;

public class FirstNameandLastNamedataProviderJson {

	private WebDriver driver;
	private String baseUrl = "https://qa-practice.netlify.app/bugs-form.html";

	@BeforeClass
	public void setUp() {
		// Set the path to the chromedriver executable
		// Not needed due to 4.11 version of selenium
		// System.setProperty("webdriver.chrome.driver",
		// "D:\\Users\\AKSHAY\\new-eclipse-workspace\\TestAkshaySeleniumProject\\driver\\chromedriver_win32\\chromedriver.exe");

		// Initialize ChromeDriver
		driver = new ChromeDriver();
		driver.get("https://qa-practice.netlify.app/bugs-form.html");
		driver.manage().window().maximize();
	}

	@DataProvider(name = "nameData")
	public Object[][] provideData() {

		return JsonTestDataFetcher.fetchTestData();

	}

	@Test(dataProvider = "nameData")
	public void verifyFirstAndLastName(String firstName, String lastName) throws InterruptedException {
		// Step 1: Locate the First Name input box and enter the provided value
		WebElement firstNameInput = driver.findElement(By.xpath("//input[@id='firstName']"));
		firstNameInput.clear();
		firstNameInput.sendKeys(firstName);
		Thread.sleep(5000);
		// Step 2: Locate the Last Name input box and enter the provided value
		WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='lastName']"));
		lastNameInput.clear();
		lastNameInput.sendKeys(lastName);
		Thread.sleep(5000);
		// Step 3: Verify First Name and Last Name
		String actualFirstName = firstNameInput.getAttribute("value");
		// Thread.sleep(5000);

		String actualLastName = lastNameInput.getAttribute("value");

		Assert.assertEquals(actualFirstName, firstName, "First Name is not as expected");
		Assert.assertEquals(actualLastName, lastName, "Last Name is not as expected");
	}

	@AfterClass
	public void tearDown() {
		// Close the browser
		if (driver != null) {
			driver.quit();
		}
	}
}
