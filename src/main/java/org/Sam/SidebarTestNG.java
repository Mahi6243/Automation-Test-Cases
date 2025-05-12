package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SidebarTestNG {
	 WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();  // Replace with WebDriverManager setup if needed
	        driver.manage().window().maximize();
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @AfterClass
	    public void teardown() {
	        driver.quit();
	    }

	    @Test(priority = 1)
	    public void TC_061_verifySidebarVisibility() {
	        WebElement sidebar = driver.findElement(By.id("cat"));
	        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar should be visible on the Home page");
	    }

	    @Test(priority = 2)
	    public void TC_062_verifySidebarAlignment() {
	        WebElement sidebar = driver.findElement(By.id("cat"));
	        int sidebarX = sidebar.getLocation().getX();
	        Assert.assertTrue(sidebarX < 100, "Sidebar should be aligned to the left");
	    }

	    @Test(priority = 3)
	    public void TC_063_verifySidebarContentLoaded() {
	        WebElement phonesCategory = driver.findElement(By.linkText("Phones"));
	        WebElement laptopsCategory = driver.findElement(By.linkText("Laptops"));
	        WebElement monitorsCategory = driver.findElement(By.linkText("Monitors"));

	        Assert.assertTrue(phonesCategory.isDisplayed(), "Phones category should be visible");
	        Assert.assertTrue(laptopsCategory.isDisplayed(), "Laptops category should be visible");
	        Assert.assertTrue(monitorsCategory.isDisplayed(), "Monitors category should be visible");
	    }

	    @Test(priority = 4)
	    public void TC_064_verifySidebarResponsiveness() throws InterruptedException {
	        // Resize to simulate a mobile device
	        driver.manage().window().setSize(new Dimension(375, 667));
	        Thread.sleep(2000);  // Allow time for responsive behavior to kick in

	        WebElement sidebar = driver.findElement(By.id("cat"));
	        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar should still be visible or adjusted on small screens");

	        // Restore size
	        driver.manage().window().maximize();
	    }
	    @Test(priority = 5)
	    public void TC_065_verifySidebarVisibilityAfterRefresh() {
	        driver.navigate().refresh();
	        WebElement sidebar = driver.findElement(By.id("cat"));
	        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar should remain visible after page refresh");
	    }
}
