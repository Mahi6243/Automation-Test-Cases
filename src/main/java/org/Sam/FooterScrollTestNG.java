package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class FooterScrollTestNG extends BaseClass{

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @Test(priority = 1)
    public void TC_153_verifyFooterVisibleAfterScroll() throws InterruptedException {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000); // wait for scroll
        WebElement footer = driver.findElement(By.tagName("footer"));
        Assert.assertTrue(footer.isDisplayed(), "Footer is not visible at the bottom after scrolling.");
    }

    @Test(priority = 2)
    public void TC_156_verifyFooterWithDynamicContent() throws InterruptedException {
        // Simulate multiple scrolls for lazy loading if applicable
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 1000)");
            Thread.sleep(1000);
        }
        WebElement footer = driver.findElement(By.tagName("footer"));
        Assert.assertTrue(footer.isDisplayed(), "Footer not visible after dynamic content load.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
