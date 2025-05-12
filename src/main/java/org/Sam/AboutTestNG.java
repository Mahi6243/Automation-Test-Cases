package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class AboutTestNG extends BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com");
    }

    @Test
    public void verifyAboutUsLinkNavigation() throws InterruptedException {
        // Find the "About us" link and click it
        WebElement aboutUsLink = driver.findElement(By.linkText("About us"));
        Assert.assertTrue(aboutUsLink.isDisplayed(), "'About us' link should be visible.");
        aboutUsLink.click();

        // Wait for modal or page content to load
        Thread.sleep(2000); // Consider WebDriverWait in real tests

        // Verify that About Us modal or content is displayed (YouTube video is expected)
        WebElement modal = driver.findElement(By.id("videoModal"));
        Assert.assertTrue(modal.isDisplayed(), "'About us' modal should be displayed.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
