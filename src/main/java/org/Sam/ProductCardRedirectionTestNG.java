package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class ProductCardRedirectionTestNG extends BaseClass{
	 WebDriver driver;
	    String baseUrl = "https://www.demoblaze.com/index.html";

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get(baseUrl);
	    }

	    @Test(priority = 1)
	    public void TC_123_verifyRedirectionOnClick() {
	        WebElement firstProduct = driver.findElement(By.cssSelector(".card-title a"));
	        String productName = firstProduct.getText();
	        firstProduct.click();

	        WebElement detailTitle = driver.findElement(By.cssSelector(".name"));
	        Assert.assertTrue(detailTitle.isDisplayed(), "Detail page title not displayed.");
	        Assert.assertEquals(detailTitle.getText(), productName, "Product name mismatch on detail page.");
	    }

	    @Test(priority = 2)
	    public void TC_124_verifyCorrectProductDetails() {
	        WebElement title = driver.findElement(By.cssSelector(".name"));
	        WebElement price = driver.findElement(By.cssSelector(".price-container"));
	        WebElement image = driver.findElement(By.cssSelector("#imgp img"));
	        WebElement description = driver.findElement(By.cssSelector("#more-information"));

	        Assert.assertTrue(title.isDisplayed() && !title.getText().isEmpty());
	        Assert.assertTrue(price.isDisplayed() && price.getText().contains("$"));
	        Assert.assertTrue(image.isDisplayed());
	        Assert.assertTrue(description.isDisplayed());
	    }

	    @Test(priority = 3)
	    public void TC_125_verifyURLStructureAfterRedirection() {
	        String currentUrl = driver.getCurrentUrl();
	        Assert.assertTrue(currentUrl.contains("prod"), "URL does not reflect product details page structure.");
	    }

	    @Test(priority = 4)
	    public void TC_126_verifyPageLoadPerformance() {
	        driver.get(baseUrl); // back to homepage
	        WebElement secondProduct = driver.findElements(By.cssSelector(".card-title a")).get(1);
	        
	        long startTime = System.currentTimeMillis();
	        secondProduct.click();
	        driver.findElement(By.cssSelector(".name")); // wait for element

	        long endTime = System.currentTimeMillis();
	        long loadTime = endTime - startTime;
	        Assert.assertTrue(loadTime < 3000, "Page took too long to load: " + loadTime + "ms");
	    }

	    @Test(priority = 5)
	    public void TC_127_verifyBackNavigation() {
	        driver.navigate().back(); // Go back to homepage
	        WebElement homepageProduct = driver.findElement(By.cssSelector(".card-title a"));
	        Assert.assertTrue(homepageProduct.isDisplayed(), "Homepage not loaded after back navigation.");
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) driver.quit();
	    }
}
