package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginPageTestNG extends BaseClass {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.demoblaze.com"; // Replace if different

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToHomePage() {
        driver.get(baseUrl);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    public void openLoginModal() {
        driver.findElement(By.id("login2")).click(); // "Log in" button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
    }

    @Test(priority = 1)
    public void TC_028_verifyLoginLinkDisplayedAndNavigates() {
        WebElement loginLink = driver.findElement(By.id("login2"));
        Assert.assertTrue(loginLink.isDisplayed());
        loginLink.click();
        WebElement loginModal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logInModal")));
        Assert.assertTrue(loginModal.isDisplayed());
    }

    @Test(priority = 2)
    public void TC_029_verifyLoginModalDisplayed() {
        openLoginModal();
        Assert.assertTrue(driver.findElement(By.id("logInModal")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("loginusername")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("loginpassword")).isDisplayed());
    }

    @Test(priority = 3)
    public void TC_030_verifyUsernamePasswordFieldsDisplayed() {
        openLoginModal();
        Assert.assertTrue(driver.findElement(By.id("loginusername")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("loginpassword")).isDisplayed());
    }

    @Test(priority = 4)
    public void TC_031_verifyTextEntryInFields() {
        openLoginModal();
        driver.findElement(By.id("loginusername")).sendKeys("user1");
        driver.findElement(By.id("loginpassword")).sendKeys("pass123");
        Assert.assertEquals(driver.findElement(By.id("loginusername")).getAttribute("value"), "user1");
        Assert.assertEquals(driver.findElement(By.id("loginpassword")).getAttribute("value"), "pass123");
    }

    @Test(priority = 5)
    public void TC_032_verifyLoginButtonAttemptsLogin() {
        openLoginModal();
        driver.findElement(By.id("loginusername")).sendKeys("user1");
        driver.findElement(By.id("loginpassword")).sendKeys("pass123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("logInModal")));
        // Validate successful login (e.g., Logout link appears)
        WebElement logoutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout2")));
        Assert.assertTrue(logoutLink.isDisplayed());
    }

    @Test(priority = 6)
    public void TC_033_verifyCloseButtonClosesModal() {
        openLoginModal();
        driver.findElement(By.xpath("//div[@id='logInModal']//button[text()='Close']")).click();
        boolean isClosed = driver.findElements(By.id("loginusername")).isEmpty();
        Assert.assertTrue(isClosed);
    }

    @Test(priority = 7)
    public void TC_034_verifyBlankUsernamePasswordErrors() {
        openLoginModal();
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("required") || alert.getText().toLowerCase().contains("error"));
        alert.accept();
    }

    @Test(priority = 8)
    public void TC_035_verifyLoginWithInvalidCredentials() {
        openLoginModal();
        driver.findElement(By.id("loginusername")).sendKeys("invalidUser");
        driver.findElement(By.id("loginpassword")).sendKeys("wrongPass");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("not valid") || alert.getText().toLowerCase().contains("wrong"));
        alert.accept();
    }
}

