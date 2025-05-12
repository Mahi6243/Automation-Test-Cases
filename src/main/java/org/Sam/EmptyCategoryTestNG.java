package org.Sam;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EmptyCategoryTestNG  extends BaseClass {
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String baseUrl = "https://www.demoblaze.com/index.html";
		driver.get(baseUrl);
    }
    @Test(priority = 1)
    public void TC_130_verifyEmptyCategoryMessage() {
        // Click a category with no products (assumes "Laptops" has no products temporarily)
        WebElement laptopsCategory = driver.findElement(By.linkText("Laptops"));
        laptopsCategory.click();

        // Wait briefly for products to load (or not)
        new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".card-block")));

        WebElement message = driver.findElement(By.id("noProductsMessage")); // Assume this is the ID used
        Assert.assertTrue(message.isDisplayed(), "No products message not visible.");
        Assert.assertEquals(message.getText().trim(), "No Products Available", "Incorrect message text.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
