package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ContectTestNG extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @Test(priority = 1)
    public void TC_004_verifyContactLinkNavigation() {
        WebElement contactLink = driver.findElement(By.xpath("//a[text()='Contact']"));
        contactLink.click();
        WebElement modal = driver.findElement(By.id("exampleModal"));
        Assert.assertTrue(modal.isDisplayed());
    }

    @Test(priority = 2)
    public void TC_005_verifyNewMessagePopupOpens() {
        WebElement modal = driver.findElement(By.id("exampleModal"));
        Assert.assertTrue(modal.isDisplayed());
    }

    @Test(priority = 3)
    public void TC_009_verifyCloseButtonClosesPopup() {
        WebElement closeBtn = driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Close']"));
        closeBtn.click();
        // Wait for modal to close
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        Assert.assertFalse(driver.findElement(By.id("exampleModal")).isDisplayed());
    }

    @Test(priority = 4)
    public void TC_010_verifySendMessageButtonFunctionality() {
        openContactForm();
        fillContactForm("John Doe", "john@example.com", "Hello Demoblaze!");
        WebElement sendBtn = driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Send message']"));
        sendBtn.click();
        handleAlert();
    }

    @Test(priority = 5)
    public void TC_011_verifyFormSubmissionWithValidInputs() {
        openContactForm();
        fillContactForm("Jane Smith", "jane@example.com", "Inquiry about laptops.");
        driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Send message']")).click();
        handleAlert();
    }

    @Test(priority = 6)
    public void TC_012_verifyErrorOnEmptyEmailField() {
        openContactForm();
        fillContactForm("John Doe", "", "Test message");
        driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Send message']")).click();
        handleAlert();
    }

    @Test(priority = 7)
    public void TC_013_verifyErrorOnInvalidEmail() {
        openContactForm();
        fillContactForm("John Doe", "invalid@", "Test message");
        driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Send message']")).click();
        handleAlert();
    }

    @Test(priority = 8)
    public void TC_014_verifyErrorOnEmptyNameField() {
        openContactForm();
        fillContactForm("", "john@example.com", "Test message");
        driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Send message']")).click();
        handleAlert();
    }

    @Test(priority = 9)
    public void TC_015_verifyErrorOnEmptyMessageField() {
        openContactForm();
        fillContactForm("John Doe", "john@example.com", "");
        driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Send message']")).click();
        handleAlert();
    }

    @Test(priority = 10)
    public void TC_016_verifyCloseDoesNotSendMessage() {
        openContactForm();
        fillContactForm("NoSubmit User", "nosubmit@example.com", "This should not send.");
        driver.findElement(By.xpath("//div[@id='exampleModal']//button[text()='Close']")).click();
        // No alert should appear
        Assert.assertFalse(isAlertPresent());
    }

    // Helper Methods
    private void openContactForm() {
        driver.findElement(By.xpath("//a[text()='Contact']")).click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    private void fillContactForm(String name, String email, String message) {
        driver.findElement(By.id("recipient-name")).clear();
        driver.findElement(By.id("recipient-name")).sendKeys(name);
        driver.findElement(By.id("recipient-email")).clear();
        driver.findElement(By.id("recipient-email")).sendKeys(email);
        driver.findElement(By.id("message-text")).clear();
        driver.findElement(By.id("message-text")).sendKeys(message);
    }

    private void handleAlert() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert says: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            Assert.fail("Expected alert not found.");
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
