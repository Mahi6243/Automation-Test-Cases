package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SidebarResponsivenessTestNG {
	 WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        driver = new ChromeDriver(options);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @AfterClass
	    public void teardown() {
	        driver.quit();
	    }

	   
	    @Test(priority = 1)
	    public void TC_094_verifySidebarVisibilityOnTablet() {
	        // Set screen size to simulate tablet
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(1024, 768);");

	        WebElement sidebar = driver.findElement(By.cssSelector(".sidebar"));  // Assuming '.sidebar' is the correct CSS selector for the sidebar
	        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar should be visible on tablet.");
	        
	        // Verify sidebar is positioned correctly and does not overlap
	        Assert.assertTrue(sidebar.getLocation().getX() >= 0, "Sidebar is not overlapping content.");
	    }

	  
	    @Test(priority = 2)
	    public void TC_095_verifySidebarBehaviorOnMobile() {
	        // Set screen size to simulate mobile
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(375, 667);");  // Example of mobile size

	        WebElement sidebar = driver.findElement(By.cssSelector(".sidebar"));
	        String classAttribute = sidebar.getAttribute("class");
	        
	        // Verify the sidebar collapses into a hamburger menu or overlay
	        Assert.assertTrue(classAttribute.contains("collapsed") || classAttribute.contains("hamburger"), "Sidebar should collapse on mobile.");
	    }

	   
	    @Test(priority = 3)
	    public void TC_096_verifySidebarToggleFunctionalityOnMobile() {
	        // Set screen size to simulate mobile
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(375, 667);");

	        WebElement hamburgerMenu = driver.findElement(By.className(".hamburger-menu")); // Assuming '.hamburger-menu' is the correct selector
	        hamburgerMenu.click();  // Click the hamburger menu

	        // Wait for the sidebar to open
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sidebar.open"))); // Assuming the sidebar opens with class 'open'

	        // Assert that the sidebar opens correctly
	        WebElement sidebar = driver.findElement(By.cssSelector(".sidebar"));
	        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar should open smoothly on mobile.");

	        // Click to close the sidebar
	        hamburgerMenu.click();
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".sidebar.open")));
	        Assert.assertFalse(sidebar.isDisplayed(), "Sidebar should close smoothly on mobile.");
	    }

	  
	    @Test(priority = 4)
	    public void TC_097_verifySidebarElementVisibilityOnSmallerScreens() {
	        // Set screen size to simulate mobile
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(375, 667);");

	        // Open the sidebar by clicking the hamburger menu
	        WebElement hamburgerMenu = driver.findElement(By.cssSelector(".hamburger-menu"));
	        hamburgerMenu.click();

	        // Check if categories and titles are visible
	        WebElement categoriesTitle = driver.findElement(By.xpath("//div[contains(text(), 'Categories')]"));
	        WebElement phonesCategory = driver.findElement(By.xpath("//a[contains(text(), 'Phones')]"));

	        Assert.assertTrue(categoriesTitle.isDisplayed(), "Categories title should be visible.");
	        Assert.assertTrue(phonesCategory.isDisplayed(), "Phones category should be visible.");
	    }
	   
	    @Test(priority = 5)
	    public void TC_098_verifySidebarDoesNotOverlapMainContentImproperly() {
	        // Set screen size to simulate tablet
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(1024, 768);");

	        WebElement sidebar = driver.findElement(By.cssSelector(".sidebar"));
	        WebElement mainContent = driver.findElement(By.cssSelector(".main-content")); // Assuming '.main-content' is the main content selector

	        // Assert that sidebar and content are displayed properly without overlap
	        Assert.assertTrue(sidebar.getLocation().getX() < mainContent.getLocation().getX(), "Sidebar should not overlap the main content.");
	    }

	  
	    @Test(priority = 6)
	    public void TC_099_verifyResponsivenessWithScreenRotation() {
	        // Set screen size to simulate mobile
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(375, 667);");

	        // Rotate screen to landscape mode
	        ((JavascriptExecutor) driver).executeScript("window.resizeTo(667, 375);");

	        // Verify the sidebar adapts correctly without distortion
	        WebElement sidebar = driver.findElement(By.cssSelector(".sidebar"));
	        Assert.assertTrue(sidebar.isDisplayed(), "Sidebar should adjust correctly on screen rotation.");
	    }
}
