package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductNameTestNG {
	 WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mahes\\eclipse-workspace\\SeleniumKGM\\Drivers\\chromedriver.exe"); // Update this path
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_116_verifyProductNameVisibility() {
	        List<WebElement> productTitles = driver.findElements(By.cssSelector(".card-title a"));
	        Assert.assertTrue(productTitles.size() > 0, "No product titles found.");

	        for (WebElement title : productTitles) {
	            Assert.assertTrue(title.isDisplayed(), "Product name is not visible.");
	            Assert.assertFalse(title.getText().trim().isEmpty(), "Product name should not be empty.");
	        }
	    }

	    @Test(priority = 2)
	    public void TC_117_verifyProductNameAccuracy() {
	        // Sample expected product names from backend (as seen on Demoblaze homepage)
	        List<String> expectedNames = List.of(
	                "Samsung galaxy s6",
	                "Nokia lumia 1520",
	                "Nexus 6",
	                "Samsung galaxy s7",
	                "Iphone 6 32gb",
	                "Sony xperia z5",
	                "HTC One M9",
	                "Sony vaio i5",
	                "Sony vaio i7",
	                "MacBook air",
	                "Dell i7 8gb",
	                "2017 Dell 15.6 Inch",
	                "MacBook Pro",
	                "Samsung galaxy s20", // Depending on rotation, this might not appear immediately
	                "Apple monitor 24",
	                "ASUS Full HD",
	                "MacBook Pro",
	                "Lenovo ThinkPad"
	        );

	        List<WebElement> productTitles = driver.findElements(By.cssSelector(".card-title a"));
	        List<String> actualNames = new ArrayList<>();

	        for (WebElement title : productTitles) {
	            actualNames.add(title.getText().trim());
	        }

	        for (String actual : actualNames) {
	            Assert.assertTrue(expectedNames.contains(actual),
	                    "Unexpected product name found: " + actual);
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) driver.quit();
	    }
}
