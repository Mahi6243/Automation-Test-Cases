package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ProductCardTestNG {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver(); // Or use WebDriverManager
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/index.html");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    // Utility method to get all product cards on the page
    private List<WebElement> getProductCards() {
        return driver.findElements(By.cssSelector(".card"));
    }

    // -------------------- TC_082 --------------------
    @Test(priority = 1)
    public void TC_082_verifyProductCardVisibility() {
        List<WebElement> productCards = getProductCards();
        Assert.assertTrue(productCards.size() > 0, "Product cards should be visible in the main content area.");
    }

    // -------------------- TC_083 --------------------
    @Test(priority = 2)
    public void TC_083_verifyProductImageDisplayed() {
        List<WebElement> productCards = getProductCards();
        for (WebElement card : productCards) {
            WebElement image = card.findElement(By.tagName("img"));
            Assert.assertTrue(image.isDisplayed(), "Product image should be displayed for each card.");
        }
    }

    // -------------------- TC_084 --------------------
    @Test(priority = 3)
    public void TC_084_verifyProductTitleVisible() {
        List<WebElement> productCards = getProductCards();
        for (WebElement card : productCards) {
            WebElement title = card.findElement(By.cssSelector(".card-title"));
            Assert.assertTrue(title.isDisplayed(), "Product title should be visible on each product card.");
        }
    }

    // -------------------- TC_085 --------------------
    @Test(priority = 4)
    public void TC_085_verifyProductPriceDisplayed() {
        List<WebElement> productCards = getProductCards();
        for (WebElement card : productCards) {
            WebElement price = card.findElement(By.cssSelector(".card-price"));
            Assert.assertTrue(price.isDisplayed(), "Product price should be displayed for each card.");
        }
    }

    // -------------------- TC_086 --------------------
    @Test(priority = 5)
    public void TC_086_verifyActionButtonsOnProductCard() {
        List<WebElement> productCards = getProductCards();
        for (WebElement card : productCards) {
            List<WebElement> buttons = card.findElements(By.cssSelector(".btn"));
            Assert.assertTrue(buttons.size() > 0, "Product card should have action buttons (e.g., Add to Cart, Details).");
            for (WebElement button : buttons) {
                Assert.assertTrue(button.isEnabled(), "Action buttons should be clickable on the product card.");
            }
        }
    }
    // -------------------- TC_087 --------------------
    @Test(priority = 6)
    public void TC_087_verifyProductCardResponsiveness() throws InterruptedException {
        // Desktop view
        driver.manage().window().setSize(new Dimension(1920, 1080));
        List<WebElement> productCards = getProductCards();
        Assert.assertTrue(productCards.size() > 0, "Product cards should be visible in desktop view.");

        // Tablet view
        driver.manage().window().setSize(new Dimension(768, 1024));
        Thread.sleep(2000);  // Wait for responsiveness to adjust
        productCards = getProductCards();
        Assert.assertTrue(productCards.size() > 0, "Product cards should be visible in tablet view.");

        // Mobile view
        driver.manage().window().setSize(new Dimension(375, 667));
        Thread.sleep(2000);  // Wait for responsiveness to adjust
        productCards = getProductCards();
        Assert.assertTrue(productCards.size() > 0, "Product cards should be visible in mobile view.");
    }

    // -------------------- TC_088 --------------------
    @Test(priority = 7)
    public void TC_088_verifyConsistencyBetweenProductCards() {
        List<WebElement> productCards = getProductCards();
        @SuppressWarnings("unused")
		String previousLayout = "";
        for (WebElement card : productCards) {
            String layout = card.getCssValue("display");
            Assert.assertTrue(layout.equals("inline-block") || layout.equals("block"),
                    "Product cards should maintain consistent layout.");
            
            // Check spacing consistency (example: margin, padding)
            String marginTop = card.getCssValue("margin-top");
            Assert.assertTrue(marginTop.equals("20px"), "Product cards should have consistent top margin.");
            
            // Ensure product cards have uniform width and height
            String width = card.getCssValue("width");
            String height = card.getCssValue("height");
            Assert.assertTrue(width.equals("220px"), "Product cards should have consistent width.");
            Assert.assertTrue(height.equals("380px"), "Product cards should have consistent height.");
        }
    }
}
