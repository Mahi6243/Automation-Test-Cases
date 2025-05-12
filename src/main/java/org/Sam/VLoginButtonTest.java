package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class VLoginButtonTest extends BaseClass {

    WebDriver driver;
    WebDriverWait wait;

    String baseUrl = "https://www.demoblaze.com"; // Replace with your actual app URL
    String username = "user123";
    String password = "Pass@1234";

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Make sure chromedriver is in your PATH
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void navigateToApp() {
        driver.get(baseUrl);
    }

    @Test(priority = 1)
    public void TC_056_verifyLoginLinkVisibleWhenNotLoggedIn() {
        WebElement loginLink = driver.findElement(By.id("login2"));
        Assert.assertTrue(loginLink.isDisplayed(), "'Log in' link should be visible when not logged in");
    }

    @Test(priority = 2)
    public void TC_057_verifyLogoutAppearsAfterSuccessfulLogin() {
        performLogin(username, password);
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        Assert.assertTrue(logoutLink.isDisplayed(), "'Logout' link should appear after login");
    }

    @Test(priority = 3)
    public void TC_058_verifyLogoutLogsOutUser() {
        performLogin(username, password);
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        logoutLink.click();
        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
        Assert.assertTrue(loginLink.isDisplayed(), "'Log in' link should reappear after logout");
    }

    @Test(priority = 4)
    public void TC_059_verifyLoginLinkAfterSessionTimeout() throws InterruptedException {
        performLogin(username, password);

        // Simulating timeout by deleting session cookies or sleeping (depends on implementation)
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
        Assert.assertTrue(loginLink.isDisplayed(), "'Log in' should appear after session timeout");
    }

    @Test(priority = 5)
    public void TC_060_verifyLogoutVisibleAfterRefresh() {
        performLogin(username, password);
        driver.navigate().refresh();

        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        Assert.assertTrue(logoutLink.isDisplayed(), "'Logout' should still be visible after refresh");
    }

    private void performLogin(String username, String password) {
        driver.findElement(By.id("login2")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
        driver.findElement(By.id("loginusername")).sendKeys(username);
        driver.findElement(By.id("loginpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
    }
}

