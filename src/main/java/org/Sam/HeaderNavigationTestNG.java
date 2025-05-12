package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class HeaderNavigationTestNG extends BaseClass {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();  // Replace with WebDriverManager or relevant setup
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test(priority = 1)
    public void TC_049_verifyHomeLinkRedirect() {
        driver.findElement(By.linkText("Home")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"), "Home page not loaded");
    }

    @Test(priority = 2)
    public void TC_050_verifyAboutUsModal() {
        driver.findElement(By.linkText("About us")).click();
        WebElement modal = driver.findElement(By.id("aboutModal"));
        Assert.assertTrue(modal.isDisplayed(), "About Us modal not displayed");
    }

    @Test(priority = 3, enabled = false) // Not applicable
    public void TC_051_verifyServicesLink() {
        // No test - Link not present
    }

    @Test(priority = 4)
    public void TC_052_verifyContactModal() {
        driver.findElement(By.linkText("Contact")).click();
        WebElement modal = driver.findElement(By.id("contactModal"));
        Assert.assertTrue(modal.isDisplayed(), "Contact modal not displayed");
    }

    @Test(priority = 5)
    public void TC_053_verifyNo404OnHeaderLinks() {
        String[] linkTexts = {"Home", "About us", "Contact"};
        for (String linkText : linkTexts) {
            driver.findElement(By.linkText(linkText)).click();
            Assert.assertFalse(driver.getPageSource().contains("404"), "404 error on link: " + linkText);
            driver.navigate().back();
        }
    }

    @Test(priority = 6)
    public void TC_054_verifyLinkContext() {
        // Example: Assume 'Cart' link should open in same tab
        String originalWindow = driver.getWindowHandle();
        driver.findElement(By.linkText("Cart")).click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), "Cart page not opened in same tab");
                driver.close();
                driver.switchTo().window(originalWindow);
            }
        }
    }
}
