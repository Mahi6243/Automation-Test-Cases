package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Year;

public class FooterCopyrightTestNG extends BaseClass{

    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @Test(priority = 1)
    public void TC_145_verifyCopyrightPresence() {
        WebElement footer = driver.findElement(By.tagName("footer"));
        Assert.assertTrue(footer.getText().toLowerCase().contains("copyright"),
                "Copyright info not present.");
    }

    @Test(priority = 2)
    public void TC_146_verifyCurrentYearInFooter() {
        WebElement footer = driver.findElement(By.tagName("footer"));
        int currentYear = Year.now().getValue();
        Assert.assertTrue(footer.getText().contains(String.valueOf(currentYear)),
                "Current year " + currentYear + " not found in footer.");
    }

    @Test(priority = 3)
    public void TC_147_verifyFooterTextStyling() {
        WebElement footer = driver.findElement(By.tagName("footer"));
        String fontSize = footer.getCssValue("font-size");
        String color = footer.getCssValue("color");

        Assert.assertNotNull(fontSize, "Footer font size not set.");
        Assert.assertNotNull(color, "Footer text color not set.");
        Assert.assertTrue(Double.parseDouble(fontSize.replace("px", "")) >= 12,
                "Footer font size is too small.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
