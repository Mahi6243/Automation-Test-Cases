package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class FooterLinkTestNG extends BaseClass{
	  WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_138_verifyPrivacyPolicyLinkPresence() {
	        WebElement privacyLink = driver.findElement(By.linkText("Privacy Policy"));
	        Assert.assertTrue(privacyLink.isDisplayed(), "Privacy Policy link is not visible.");
	    }

	    @Test(priority = 2)
	    public void TC_139_verifyTermsOfServiceLinkPresence() {
	        WebElement termsLink = driver.findElement(By.linkText("Terms of Service"));
	        Assert.assertTrue(termsLink.isDisplayed(), "Terms of Service link is not visible.");
	    }

	    @Test(priority = 3)
	    public void TC_140_verifyAboutUsLinkPresence() {
	        WebElement aboutLink = driver.findElement(By.linkText("About Us"));
	        Assert.assertTrue(aboutLink.isDisplayed(), "About Us link is not visible.");
	    }

	    @Test(priority = 4)
	    public void TC_141_verifyPrivacyPolicyNavigation() {
	        driver.findElement(By.linkText("Privacy Policy")).click();
	        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("privacy"), "Incorrect redirection to Privacy Policy.");
	        driver.navigate().back();
	    }
	    @Test(priority = 5)
	    public void TC_142_verifyTermsOfServiceNavigation() {
	        driver.findElement(By.linkText("Terms of Service")).click();
	        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("terms"), "Incorrect redirection to Terms of Service.");
	        driver.navigate().back();
	    }

	    @Test(priority = 6)
	    public void TC_143_verifyAboutUsNavigation() {
	        driver.findElement(By.linkText("About Us")).click();
	        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("about"), "Incorrect redirection to About Us.");
	        driver.navigate().back();
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}
