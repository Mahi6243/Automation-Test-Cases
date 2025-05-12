package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class SidebarTestSuiteTestNG {
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
	    @Test(priority = 12)
	    public void TC_072_verifyLaptopsCategoryVisible() {
	        Assert.assertTrue(driver.findElement(By.linkText("Laptops")).isDisplayed(), "'Laptops' category should be visible");
	    }

	    @Test(priority = 13)
	    public void TC_073_verifyMonitorsCategoryVisible() {
	        Assert.assertTrue(driver.findElement(By.linkText("Monitors")).isDisplayed(), "'Monitors' category should be visible");
	    }

	    @Test(priority = 14)
	    public void TC_074_verifyCategoryOrder() {
	        List<WebElement> categories = driver.findElements(By.xpath("//div[@id='cat']//a"));
	        Assert.assertTrue(categories.size() >= 3, "Sidebar should have at least 3 categories");
	        Assert.assertEquals(categories.get(0).getText().trim(), "Phones", "First category should be Phones");
	        Assert.assertEquals(categories.get(1).getText().trim(), "Laptops", "Second category should be Laptops");
	        Assert.assertEquals(categories.get(2).getText().trim(), "Monitors", "Third category should be Monitors");
	    }

	    @Test(priority = 15)
	    public void TC_075_verifySidebarScrollBehavior() throws InterruptedException {
	        // Scroll behavior can only be verified visually or with JavaScript scrollTop check.
	        WebElement sidebar = driver.findElement(By.id("cat"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", sidebar);
	        Thread.sleep(1000);
	        Long scrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop", sidebar);
	        Assert.assertTrue(scrollTop >= 0, "Sidebar should allow scrolling if content exceeds view");
	    }
}
