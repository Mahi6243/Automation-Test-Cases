package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginHeader {
	 WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @AfterClass
	    public void teardown() {
	        driver.quit();
	    }

	    // Utility method to login
	    private void login(String username, String password) throws InterruptedException {
	        driver.findElement(By.id("login2")).click();
	        Thread.sleep(1000); // Wait for modal
	        driver.findElement(By.id("loginusername")).sendKeys(username);
	        driver.findElement(By.id("loginpassword")).sendKeys(password);
	        driver.findElement(By.xpath("//button[text()='Log in']")).click();
	        Thread.sleep(3000); // Wait for login to complete and UI update
	    }

	    // Utility method to logout
	    private void logout() throws InterruptedException {
	        driver.findElement(By.id("logout2")).click();
	        Thread.sleep(2000);
	    }

	    @Test(priority = 1)
	    public void TC_056_verifyLoginLinkWhenNotLoggedIn() {
	        driver.navigate().refresh();
	        WebElement loginLink = driver.findElement(By.id("login2"));
	        Assert.assertTrue(loginLink.isDisplayed(), "Log in link should be displayed when not logged in");
	    }

	    @Test(priority = 2)
	    public void TC_057_verifyLogoutAppearsAfterLogin() throws InterruptedException {
	        login("user123", "Pass@1234");
	        WebElement logoutLink = driver.findElement(By.id("logout2"));
	        Assert.assertTrue(logoutLink.isDisplayed(), "Logout link should be visible after login");
	    }

	    @Test(priority = 3)
	    public void TC_058_verifyLogoutFunctionality() throws InterruptedException {
	        logout();
	        WebElement loginLink = driver.findElement(By.id("login2"));
	        Assert.assertTrue(loginLink.isDisplayed(), "Log in link should be visible after logout");
	    }

	    @Test(priority = 4)
	    public void TC_059_verifyLoginLinkAfterSessionTimeout() throws InterruptedException {
	        // Demoblaze doesn't have an actual session timeout logic via UI
	        // Simulating with manual logout instead for demonstration
	        login("user123", "Pass@1234");
	        // Simulate idle time (not actual timeout handling since the app may not enforce it)
	        Thread.sleep(15000); // Wait 15s to simulate inactivity
	        driver.navigate().refresh(); // Mimic session refresh
	        WebElement logoutLink = driver.findElement(By.id("logout2"));
	        Assert.assertTrue(logoutLink.isDisplayed(), "Session is still active, logout link should remain");
	        logout(); // Log out to clean state
	    }

	    @Test(priority = 5)
	    public void TC_060_verifyHeaderAfterPageRefresh() throws InterruptedException {
	        login("user123", "Pass@1234");
	        driver.navigate().refresh();
	        Thread.sleep(2000);
	        WebElement logoutLink = driver.findElement(By.id("logout2"));
	        Assert.assertTrue(logoutLink.isDisplayed(), "Logout should still be visible after page refresh");
	        logout(); // Clean up
	    }
}
