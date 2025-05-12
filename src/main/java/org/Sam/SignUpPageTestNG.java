package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SignUpPageTestNG extends BaseClass {

    WebDriver driver;
    WebDriverWait wait;
    String baseUrl = "https://www.demoblaze.com"; // Replace with your site

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToSite() {
        driver.get(baseUrl);
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    public void openSignupModal() {
        driver.findElement(By.id("signin2")).click(); // Replace with actual ID if different
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
    }

    @Test(priority = 1)
    public void TC_036_verifySignupLinkNavigation() {
        openSignupModal();
        WebElement username = driver.findElement(By.id("sign-username"));
        WebElement password = driver.findElement(By.id("sign-password"));
        Assert.assertTrue(username.isDisplayed() && password.isDisplayed());
    }

    @Test(priority = 2)
    public void TC_037_verifyUIElementsDisplayed() {
        openSignupModal();
        Assert.assertTrue(driver.findElement(By.id("sign-username")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("sign-password")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Sign up']")).isDisplayed());
    }

    @Test(priority = 3)
    public void TC_038_verifyEmptyFormSubmission() {
        openSignupModal();
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("required"));
        alert.accept();
    }

    @Test(priority = 4)
    public void TC_039_verifySignupWithValidCredentials() {
        openSignupModal();
        driver.findElement(By.id("sign-username")).sendKeys("user" + System.currentTimeMillis());
        driver.findElement(By.id("sign-password")).sendKeys("Pass@1234");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("sign up successful"));
        alert.accept();
    }

    @Test(priority = 5)
    public void TC_040_verifySignupWithOnlyUsername() {
        openSignupModal();
        driver.findElement(By.id("sign-username")).sendKeys("user123");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("password"));
        alert.accept();
    }

    @Test(priority = 6)
    public void TC_041_verifySignupWithOnlyPassword() {
        openSignupModal();
        driver.findElement(By.id("sign-password")).sendKeys("Pass@1234");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("username"));
        alert.accept();
    }

    @Test(priority = 7)
    public void TC_042_verifyInvalidUsernameFormat() {
        openSignupModal();
        driver.findElement(By.id("sign-username")).sendKeys("@@@");
        driver.findElement(By.id("sign-password")).sendKeys("Pass@1234");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("invalid"));
        alert.accept();
    }

    @Test(priority = 8)
    public void TC_043_verifyPasswordComplexity() {
        openSignupModal();
        driver.findElement(By.id("sign-username")).sendKeys("user123");
        driver.findElement(By.id("sign-password")).sendKeys("123");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("complexity"));
        alert.accept();
    }

    @Test(priority = 9)
    public void TC_044_verifyCloseButtonFunctionality() {
        openSignupModal();
        driver.findElement(By.xpath("//button[text()='Close']")).click();
        boolean isClosed = driver.findElements(By.id("sign-username")).isEmpty();
        Assert.assertTrue(isClosed, "Modal should be closed");
    }

    @Test(priority = 10)
    public void TC_045_verifyFieldMaxLengthRestriction() {
        openSignupModal();
        String longInput = "a".repeat(256);
        driver.findElement(By.id("sign-username")).sendKeys(longInput);
        driver.findElement(By.id("sign-password")).sendKeys(longInput);
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("too long") || alert.getText().toLowerCase().contains("error"));
        alert.accept();
    }

    @Test(priority = 11)
    public void TC_046_verifyXButtonFunctionality() {
        openSignupModal();
        WebElement closeIcon = driver.findElement(By.xpath("//div[@id='signInModal']//button[@class='close']"));
        closeIcon.click();
        boolean isClosed = driver.findElements(By.id("sign-username")).isEmpty();
        Assert.assertTrue(isClosed);
    }

    @Test(priority = 12)
    public void TC_047_verifySuccessfulRedirectionAfterSignup() {
        openSignupModal();
        String uniqueUser = "user" + System.currentTimeMillis();
        driver.findElement(By.id("sign-username")).sendKeys(uniqueUser);
        driver.findElement(By.id("sign-password")).sendKeys("Newpass@123");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertTrue(alert.getText().toLowerCase().contains("sign up successful"));
        alert.accept();
        // Optional: validate dashboard redirection if modal auto-closes
    }
}
