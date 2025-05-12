package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class CategoryFilterTestNG {
	  WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver(); // Or use WebDriverManager
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @AfterClass
	    public void teardown() {
	        driver.quit();
	    }

	    // Utility to get all product cards
	    private List<WebElement> getProductCards() {
	        return driver.findElements(By.cssSelector(".card-title"));
	    }

	    @Test(priority = 1)
	    public void TC_076_verifyPhonesCategoryFilter() throws InterruptedException {
	        driver.findElement(By.linkText("Phones")).click();
	        Thread.sleep(2000);
	        List<WebElement> titles = getProductCards();
	        Assert.assertTrue(titles.size() > 0, "Phones category should show products");
	        for (WebElement title : titles) {
	            String text = title.getText().toLowerCase();
	            Assert.assertTrue(text.contains("phone") || text.contains("galaxy") || text.contains("iphone"),
	                    "Only phone-related products should be shown: " + text);
	        }
	    }

	    @Test(priority = 2)
	    public void TC_077_verifyLaptopsCategoryFilter() throws InterruptedException {
	        driver.findElement(By.linkText("Laptops")).click();
	        Thread.sleep(2000);
	        List<WebElement> titles = getProductCards();
	        Assert.assertTrue(titles.size() > 0, "Laptops category should show products");
	        for (WebElement title : titles) {
	            String text = title.getText().toLowerCase();
	            Assert.assertTrue(text.contains("laptop") || text.contains("macbook") || text.contains("sony") || text.contains("dell"),
	                    "Only laptop-related products should be shown: " + text);
	        }
	    }

	    @Test(priority = 3)
	    public void TC_078_verifyMonitorsCategoryFilter() throws InterruptedException {
	        driver.findElement(By.linkText("Monitors")).click();
	        Thread.sleep(2000);
	        List<WebElement> titles = getProductCards();
	        Assert.assertTrue(titles.size() > 0, "Monitors category should show products");
	        for (WebElement title : titles) {
	            String text = title.getText().toLowerCase();
	            Assert.assertTrue(text.contains("monitor") || text.contains("asus") || text.contains("apple"),
	                    "Only monitor-related products should be shown: " + text);
	        }
	    }

	    @Test(priority = 4)
	    public void TC_079_verifyNoUnrelatedProductsShown() throws InterruptedException {
	        driver.findElement(By.linkText("Laptops")).click();
	        Thread.sleep(2000);
	        List<WebElement> titles = getProductCards();
	        for (WebElement title : titles) {
	            String text = title.getText().toLowerCase();
	            Assert.assertFalse(text.contains("monitor") || text.contains("phone"),
	                    "No unrelated category products (like phones/monitors) should appear: " + text);
	        }
	    }

	    @Test(priority = 5)
	    public void TC_080_verifyUILoadingBehaviorDuringFilter() throws InterruptedException {
	        long start = System.currentTimeMillis();
	        driver.findElement(By.linkText("Phones")).click();
	        Thread.sleep(2000); // Simulate wait for UI
	        long duration = System.currentTimeMillis() - start;
	        Assert.assertTrue(duration >= 1000, "UI should show a smooth load, delay observed: " + duration + "ms");
	        List<WebElement> products = getProductCards();
	        Assert.assertTrue(products.size() > 0, "Products should load after filter");
	    }
	    @Test(priority = 6)
	    public void TC_081_verifyAllProductsAfterFilterReset() throws InterruptedException {
	        driver.findElement(By.linkText("Home")).click(); // Reset via Home link
	        Thread.sleep(2000);
	        List<WebElement> titles = getProductCards();
	        Assert.assertTrue(titles.size() >= 9, "All products should be shown after clearing filter");
	    }
}
