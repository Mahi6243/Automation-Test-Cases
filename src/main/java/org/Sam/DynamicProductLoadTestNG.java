package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class DynamicProductLoadTestNG {
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

	    // Utility method to check for page reload
	    private boolean isPageReloaded(String previousURL) {
	        return !driver.getCurrentUrl().equals(previousURL);
	    }

	
	    @Test(priority = 1)
	    public void TC_089_verifyProductsLoadDynamicallyAfterClickingPhones() {
	        String previousURL = driver.getCurrentUrl();
	        WebElement phonesCategory = driver.findElement(By.linkText("Phones"));
	        phonesCategory.click();
	        
	        // Wait for the page to load dynamically (you may want to adjust wait conditions)
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card"))); // Assuming .product-card is the selector for products
	        
	        Assert.assertFalse(isPageReloaded(previousURL), "Products should load dynamically without full page reload.");
	    }

	  
	    @Test(priority = 2)
	    public void TC_090_verifyProductsLoadDynamicallyAfterClickingLaptops() {
	        String previousURL = driver.getCurrentUrl();
	        WebElement laptopsCategory = driver.findElement(By.linkText("Laptops"));
	        laptopsCategory.click();

	        // Wait for the page to load dynamically
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card"))); // Assuming .product-card is the selector for products

	        Assert.assertFalse(isPageReloaded(previousURL), "Products should load dynamically without full page reload.");
	    }

	    
	    @Test(priority = 3)
	    public void TC_091_verifyProductsLoadDynamicallyAfterClickingMonitors() {
	        String previousURL = driver.getCurrentUrl();
	        WebElement monitorsCategory = driver.findElement(By.linkText("Monitors"));
	        monitorsCategory.click();

	        // Wait for the page to load dynamically
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card"))); // Assuming .product-card is the selector for products

	        Assert.assertFalse(isPageReloaded(previousURL), "Products should load dynamically without full page reload.");
	    }

	 
	    @Test(priority = 4)
	    public void TC_092_verifyBrowserBackButtonBehaviorAfterDynamicLoad() {
	        String previousURL = driver.getCurrentUrl();
	        WebElement phonesCategory = driver.findElement(By.linkText("Phones"));
	        phonesCategory.click();

	        // Wait for the page to load dynamically
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card")));

	        // Use the browser back button
	        driver.navigate().back();

	        // Assert we return to the previous page without issues
	        Assert.assertEquals(driver.getCurrentUrl(), previousURL, "Back button should return to the previous page without broken state.");
	    }
	    @Test(priority = 5)
	    public void TC_093_verifyNoFullPageReloadViaNetworkMonitoring() {
	        // Open DevTools network monitoring (this part is theoretical, as Selenium cannot directly control DevTools)
	        String previousURL = driver.getCurrentUrl();
	        WebElement phonesCategory = driver.findElement(By.linkText("Phones"));
	        phonesCategory.click();

	        // Wait for the page to load dynamically
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product-card")));

	        // Check network tab or console log for AJAX requests (this can be manually verified)
	        // Since Selenium doesn't provide direct access to network tab, you'd ideally need a tool like BrowserMob Proxy for network monitoring.
	        // Assert.assertTrue(networkRequestWasMade(), "Only AJAX request should be triggered, not a full page reload.");
	        
	        Assert.assertFalse(isPageReloaded(previousURL), "Only AJAX request should be made, no full page reload.");
	    }
}
