package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ProductTextTruncationTestNG extends BaseClass{
	  WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().window().setSize(new Dimension(1366, 768)); // Desktop view
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_131_verifyProductNameTruncation() {
	        List<WebElement> productTitles = driver.findElements(By.cssSelector(".card-title a"));
	        for (WebElement title : productTitles) {
	            String css = title.getCssValue("text-overflow");
	            @SuppressWarnings("unused")
				String overflow = title.getCssValue("overflow");
	            String whiteSpace = title.getCssValue("white-space");

	            Assert.assertTrue(css.contains("ellipsis") || whiteSpace.contains("nowrap"),
	                "Product title truncation not applied correctly.");
	        }
	    }

	    @Test(priority = 2)
	    public void TC_132_verifyProductDescriptionTruncation() {
	        List<WebElement> descriptions = driver.findElements(By.cssSelector(".card-text"));
	        for (WebElement desc : descriptions) {
	            String css = desc.getCssValue("text-overflow");
	            String overflow = desc.getCssValue("overflow");
	            Assert.assertTrue(css.contains("ellipsis") || overflow.contains("hidden"),
	                "Description text truncation not applied.");
	        }
	    }

	    @Test(priority = 3)
	    public void TC_133_verifyTooltipForFullText() {
	        List<WebElement> productTitles = driver.findElements(By.cssSelector(".card-title a"));
	        for (WebElement title : productTitles) {
	            String tooltip = title.getAttribute("title");
	            if (title.getText().length() >= 30) { // Assuming truncation kicks in >30 chars
	                Assert.assertTrue(tooltip != null && !tooltip.isEmpty(),
	                    "Tooltip not found for truncated product title.");
	            }
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}
