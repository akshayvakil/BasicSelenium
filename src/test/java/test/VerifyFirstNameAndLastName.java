package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class VerifyFirstNameAndLastName {


    private WebDriver driver;
    private String baseUrl = "https://qa-practice.netlify.app/bugs-form.html";

    @BeforeClass
    public void setUp() {
        // Set the path to the chromedriver executable
     //   System.setProperty("webdriver.chrome.driver", "D:\\Users\\AKSHAY\\new-eclipse-workspace\\TestAkshaySeleniumProject\\driver\\chromedriver_win32\\chromedriver.exe");

        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.get("https://qa-practice.netlify.app/bugs-form.html");
        driver.manage().window().maximize();
    }

    	@Test
        public void verifyFirstAndLastName() {
            // Step 1: Locate the First Name input box and enter the value "Sidharth"
            WebElement firstNameInput = driver.findElement(By.xpath("//input[@id='firstName']"));
            firstNameInput.sendKeys("Sidharth");

            // Step 2: Locate the Last Name input box and enter the value "Shukla"
            WebElement lastNameInput = driver.findElement(By.xpath("//input[@id='lastName']"));
            lastNameInput.sendKeys("Shukla");

            // Step 3: Verify First Name and Last Name
            String actualFirstName = firstNameInput.getAttribute("value");
            String actualLastName = lastNameInput.getAttribute("value");

            Assert.assertEquals(actualFirstName, "Sidharth", "First Name is not as expected");
            Assert.assertEquals(actualLastName, "Shukla", "Last Name is not as expected");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
