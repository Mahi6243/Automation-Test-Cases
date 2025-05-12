package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ProductCardTestsNG extends BaseClass {
	 WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_104_verifyProductCardsAreVisible() {
	        List<WebElement> cards = driver.findElements(By.cssSelector(".card.h-100"));
	        Assert.assertTrue(cards.size() > 0, "Product cards should be visible on the homepage.");
	    }

	    @Test(priority = 2)
	    public void TC_105_verifyProductImagesDisplayed() {
	        List<WebElement> images = driver.findElements(By.cssSelector(".card-img-top.img-fluid"));
	        for (WebElement img : images) {
	            Assert.assertTrue(img.isDisplayed(), "Product image should be displayed.");
	            Assert.assertFalse(img.getAttribute("src").isEmpty(), "Image src should not be empty.");
	        }
	    }

	    @Test(priority = 3)
	    public void TC_106_verifyProductTitlesDisplayed() {
	        List<WebElement> titles = driver.findElements(By.cssSelector(".card-title a"));
	        for (WebElement title : titles) {
	            Assert.assertTrue(title.isDisplayed(), "Product title should be visible.");
	            Assert.assertFalse(title.getText().isEmpty(), "Product title should not be empty.");
	        }
	    }

	    @Test(priority = 4)
	    public void TC_107_verifyProductPricesDisplayed() {
	        List<WebElement> prices = driver.findElements(By.cssSelector(".card-block .price-container"));
	        for (WebElement price : prices) {
	            Assert.assertTrue(price.isDisplayed(), "Product price should be visible.");
	            Assert.assertFalse(price.getText().isEmpty(), "Product price text should not be empty.");
	        }
	    }

	    @Test(priority = 5)
	    public void TC_108_verifyActionButtonsPresent() {
	        List<WebElement> detailsButtons = driver.findElements(By.cssSelector(".card-title a"));
	        for (WebElement btn : detailsButtons) {
	            Assert.assertTrue(btn.isDisplayed(), "\"Details\" button (title link) should be visible.");
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) driver.quit();
	    }
}
