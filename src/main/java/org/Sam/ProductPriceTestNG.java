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
import java.util.regex.Pattern;

public class ProductPriceTestNG extends BaseClass {
	 WebDriver driver;

	    @BeforeClass
	    public void setUp() {
//	        System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\mahes\\\\eclipse-workspace\\\\SeleniumKGM\\\\Drivers\\\\chromedriver.exe"); // Update with your path
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_119_verifyProductPriceVisibility() {
	        List<WebElement> prices = driver.findElements(By.cssSelector(".card-block .price-container"));
	        Assert.assertTrue(prices.size() > 0, "No product prices found.");
	        for (WebElement price : prices) {
	            Assert.assertTrue(price.isDisplayed(), "Product price should be visible.");
	            Assert.assertFalse(price.getText().trim().isEmpty(), "Product price should not be empty.");
	        }
	    }

	    @Test(priority = 2)
	    public void TC_120_verifyProductPriceAccuracy() {
	        // Simulated expected prices based on Demoblaze (must match homepage list order)
	        List<String> expectedPrices = List.of(
	            "$360", "$820", "$650", "$800", "$790", "$320", "$700",
	            "$790", "$790", "$700", "$700", "$700", "$1100", "$120", "$230", "$400", "$180", "$1000"
	        );

	        List<WebElement> priceElements = driver.findElements(By.cssSelector(".card-block .price-container"));
	        List<String> actualPrices = new ArrayList<>();

	        for (WebElement price : priceElements) {
	            actualPrices.add(price.getText().trim());
	        }

	        for (String actual : actualPrices) {
	            Assert.assertTrue(expectedPrices.contains(actual), "Unexpected price found: " + actual);
	        }
	    }

	    @Test(priority = 3)
	    public void TC_121_verifyCorrectPriceFormat() {
	        List<WebElement> priceElements = driver.findElements(By.cssSelector(".card-block .price-container"));
	        Pattern pricePattern = Pattern.compile("^\\$\\d{2,5}(\\.\\d{2})?$"); // e.g., $360 or $360.00

	        for (WebElement price : priceElements) {
	            String text = price.getText().trim();
	            Assert.assertTrue(pricePattern.matcher(text).matches(),
	                    "Price format incorrect: " + text);
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) driver.quit();
	    }

}
