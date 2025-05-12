package org.Sam;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class FooterContactTestNG extends BaseClass{
	 WebDriver driver;

	    @BeforeClass
	    public void setup() {
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://www.demoblaze.com/index.html");
	    }

	    @Test(priority = 1)
	    public void TC_148_verifyEmailPresence() {
	        WebElement email = driver.findElement(By.xpath("//*[contains(text(),'@')]"));
	        Assert.assertTrue(email.isDisplayed(), "Email address not visible in footer.");
	    }

	    @Test(priority = 2)
	    public void TC_149_verifyPhoneNumberPresence() {
	        WebElement phone = driver.findElement(By.xpath("//*[contains(text(),'+') or contains(text(),'Phone')]"));
	        Assert.assertTrue(phone.isDisplayed(), "Phone number not visible in footer.");
	    }

	    @Test(priority = 3)
	    public void TC_150_verifyEmailLinkFunctionality() {
	        WebElement mailLink = driver.findElement(By.xpath("//a[starts-with(@href,'mailto:')]"));
	        String href = mailLink.getAttribute("href");
	        Assert.assertTrue(href.startsWith("mailto:"), "Email link not functioning correctly.");
	    }

	    @Test(priority = 4)
	    public void TC_151_verifyPhoneLinkFunctionality() {
	        WebElement phoneLink = driver.findElement(By.xpath("//a[starts-with(@href,'tel:')]"));
	        String href = phoneLink.getAttribute("href");
	        Assert.assertTrue(href.startsWith("tel:"), "Phone link not functioning correctly.");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
}
