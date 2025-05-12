package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TS_001toTS_003 extends BaseClass {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // TC_001 - Verify logo is visible and correctly positioned
    @Test(priority = 1)
    public void verifyLogoDisplay() {
        WebElement logo = driver.findElement(By.id("nava"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not visible");
        Assert.assertEquals(logo.getText(), "PRODUCT STORE", "Logo text is incorrect");
    }

    // TC_002 - Verify clicking logo redirects to Home page
    @Test(priority = 2)
    public void verifyLogoRedirectsToHome() {
        WebElement logo = driver.findElement(By.id("nava"));
        logo.click();
        String expectedUrl = "https://www.demoblaze.com/index.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Clicking logo did not redirect to Home page");
    }

    // TC_003 - Verify "Home" link navigates to the Home page
    @Test(priority = 3)
    public void verifyHomeLinkNavigation() {
        WebElement homeLink = driver.findElement(By.xpath("//a[text()='Home ']"));
        homeLink.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("index.html"), "Home link did not navigate to Home page");
    }
}
