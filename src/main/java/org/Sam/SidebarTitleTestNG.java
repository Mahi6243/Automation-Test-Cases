package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class SidebarTitleTestNG extends BaseClass {
	 WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();  // Or WebDriverManager setup
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        driver.manage().window().maximize();
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @AfterClass
	    public void teardown() {
	        driver.quit();
	    }

//	    @Test(priority = 1)
//	    public void TC_066_verifySidebarTitleVisibility() {
//	        WebElement title = driver.findElement(By.xpath("//h5[text()='Categories']"));
//	        Assert.assertTrue(title.isDisplayed(), "'Categories' title should be visible");
//	    }

	    @Test(priority = 2)
	    public void TC_067_verifySidebarTitleText() {
	        WebElement title = driver.findElement(By.xpath("//h5"));
	        Assert.assertEquals(title.getText().trim(), "Categories", "Sidebar title text should be 'Categories'");
	    }
//
//	    @Test(priority = 3)
//	    public void TC_068_verifySidebarTitleStyle() {
//	        WebElement title = driver.findElement(By.xpath("//h5[text()='Categories']"));
//	        String fontWeight = title.getCssValue("font-weight");
//	        String fontSize = title.getCssValue("font-size");
//	        String textAlign = title.getCssValue("text-align");
//
//	        // Convert font-weight to int if possible, some browsers return "bold", others return "700"
//	        int weight = fontWeight.matches("\\d+") ? Integer.parseInt(fontWeight) : (fontWeight.equalsIgnoreCase("bold") ? 700 : 400);
//
//	        Assert.assertTrue(weight >= 600, "Font weight should indicate bold style");
//	        Assert.assertTrue(fontSize.contains("16") || fontSize.contains("1"), "Font size should be 16px or equivalent");
//	        Assert.assertTrue(textAlign.equals("left") || textAlign.equals("start"), "Title should be left-aligned");
//	    }
//
//	    @Test(priority = 4)
//	    public void TC_069_verifySidebarTitleResponsiveness() throws InterruptedException {
//	        driver.manage().window().setSize(new Dimension(375, 667)); // Simulate mobile screen
//	        Thread.sleep(2000);
//	        WebElement title = driver.findElement(By.xpath("//h5[text()='Categories']"));
//	        Assert.assertTrue(title.isDisplayed(), "Title should remain visible on smaller screens");
//	        driver.manage().window().maximize(); // Restore window size
//	    }
//
//	    @Test(priority = 5)
//	    public void TC_070_verifySidebarTitleAfterPageReload() {
//	        driver.navigate().refresh();
//	        WebElement title = driver.findElement(By.xpath("//h5[text()='Categories']"));
//	        Assert.assertTrue(title.isDisplayed(), "Sidebar title should persist after page refresh");
//	    }
}
