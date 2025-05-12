package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class FooterResponsiveTestNG extends BaseClass{
	  WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	    }

	    @Test(priority = 1)
	    public void TC_158_verifyFooterOnDesktop() {
	        driver.manage().window().setSize(new Dimension(1280, 900));
	        driver.get("https://www.demoblaze.com/index.html");
	        WebElement footer = driver.findElement(By.tagName("footer"));
	        Assert.assertTrue(footer.isDisplayed(), "Footer not visible on desktop.");
	    }

	    @Test(priority = 2)
	    public void TC_159_verifyFooterOnTablet() {
	        driver.manage().window().setSize(new Dimension(800, 1024));
	        driver.navigate().refresh();
	        WebElement footer = driver.findElement(By.tagName("footer"));
	        Assert.assertTrue(footer.isDisplayed(), "Footer not visible on tablet.");
	    }

	    @Test(priority = 3)
	    public void TC_160_verifyFooterOnMobile() {
	        driver.manage().window().setSize(new Dimension(375, 667));
	        driver.navigate().refresh();
	        WebElement footer = driver.findElement(By.tagName("footer"));
	        Assert.assertTrue(footer.isDisplayed(), "Footer not visible on mobile.");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}
