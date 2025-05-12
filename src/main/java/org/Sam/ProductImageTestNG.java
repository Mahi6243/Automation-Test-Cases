package org.Sam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class ProductImageTestNG extends BaseClass{
	 WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	        driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_112_verifyNoBrokenImages() {
	        List<WebElement> images = driver.findElements(By.cssSelector(".card-img-top.img-fluid"));
	        for (WebElement img : images) {
	            String src = img.getAttribute("src");
	            Assert.assertNotNull(src, "Image source (src) should not be null.");
	            Assert.assertFalse(src.trim().isEmpty(), "Image src should not be empty.");

	            // Check image is rendered (width/height > 0)
	            Boolean imageLoaded = (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
	                    .executeScript(
	                            "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
	                            img);
	            Assert.assertTrue(imageLoaded, "Image failed to load or is broken: " + src);
	        }
	    }

	    @Test(priority = 2)
	    public void TC_113_verifyImageAltTextPresent() {
	        List<WebElement> images = driver.findElements(By.cssSelector(".card-img-top.img-fluid"));
	        for (WebElement img : images) {
	            String alt = img.getAttribute("alt");
	            Assert.assertNotNull(alt, "Alt text must be present.");
	            Assert.assertFalse(alt.trim().isEmpty(), "Alt text should not be empty.");
	        }
	    }

	    @Test(priority = 3)
	    public void TC_114_verifyImageAspectRatioConsistency() {
	        List<WebElement> images = driver.findElements(By.cssSelector(".card-img-top.img-fluid"));

	        double referenceRatio = -1;
	        for (WebElement img : images) {
	            int width = img.getSize().getWidth();
	            int height = img.getSize().getHeight();
	            Assert.assertTrue(width > 0 && height > 0, "Image must have width and height.");

	            double currentRatio = (double) width / height;

	            if (referenceRatio == -1) {
	                referenceRatio = currentRatio; // First image as baseline
	            } else {
	                double tolerance = 0.1; // Allow 10% variance
	                Assert.assertTrue(Math.abs(referenceRatio - currentRatio) < tolerance,
	                        "Inconsistent image aspect ratio detected.");
	            }
	        }
	    }

	    @AfterClass
	    public void tearDown() {
	        if (driver != null) driver.quit();
	    }
}
