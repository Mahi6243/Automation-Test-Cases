package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.testng.Assert;

import java.time.Duration;

public class CartTestNG extends BaseClass {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }

    @Test(priority = 1)
    public void TC_022_verifyCartLinkNavigation() {
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        Assert.assertTrue(cartLink.isDisplayed(), "Cart link should be visible.");
        cartLink.click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("cart.html"), "Should navigate to Cart page.");
    }

    @SuppressWarnings("unused")
	@Test(priority = 2)
    public void TC_023_verifyCartPageHeaders() {
        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tbodyid")));
        Assert.assertTrue(driver.findElement(By.xpath("//th[text()='Pic']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//th[text()='Title']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//th[text()='Price']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//th[text()='x']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//button[text()='Place Order']")).isDisplayed());
    }

    @Test(priority = 3)
    public void TC_024_verifyEmptyCart() {
        WebElement tableBody = driver.findElement(By.id("tbodyid"));
        Assert.assertEquals(tableBody.getText().trim(), "", "Cart should be empty initially.");
    }

    @Test(priority = 4)
    public void TC_025_verifyPlaceOrderButton() {
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[text()='Place Order']"));
        Assert.assertTrue(placeOrderButton.isDisplayed(), "Place Order button should be visible.");
        Assert.assertTrue(placeOrderButton.isEnabled(), "Place Order button should be clickable.");
    }

    @Test(priority = 5)
    public void TC_026_placeOrderWithoutProducts() {
        WebElement placeOrderButton = driver.findElement(By.xpath("//button[text()='Place Order']"));
        placeOrderButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("orderModal")));
        WebElement modal = driver.findElement(By.id("orderModal"));
        Assert.assertTrue(modal.isDisplayed(), "Order modal should appear even if cart is empty.");
    }

    @Test(priority = 6)
    public void TC_027_verifyResponsiveLayout() {
        // Mobile
        driver.manage().window().setSize(new Dimension(375, 667));
        Assert.assertTrue(driver.findElement(By.id("cartur")).isDisplayed(), "Cart should be accessible on mobile.");
        Assert.assertTrue(driver.findElement(By.id("tbodyid")).isDisplayed());

        // Tablet
        driver.manage().window().setSize(new Dimension(768, 1024));
        Assert.assertTrue(driver.findElement(By.id("cartur")).isDisplayed(), "Cart should be accessible on tablet.");
        Assert.assertTrue(driver.findElement(By.id("tbodyid")).isDisplayed());

        // Desktop
        driver.manage().window().maximize();
        Assert.assertTrue(driver.findElement(By.id("cartur")).isDisplayed(), "Cart should be accessible on desktop.");
        Assert.assertTrue(driver.findElement(By.id("tbodyid")).isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
