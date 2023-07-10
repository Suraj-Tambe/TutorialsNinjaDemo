package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.utils.Utilities;

public class Register extends Base{
	WebDriver driver;
	public Register() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		driver=initializeBrowser(prop.getProperty("browserName"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();	
	}
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithmandatoryFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telePhone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualText=driver.findElement(By.xpath("//div[@id=\"content\"]//h1")).getText();
		Assert.assertEquals(actualText,dataProp.getProperty("registerText"), "Account not created");
	}
	@Test(priority=2)
	public void verifyRegisteringAnAccountWithAllFields() {
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telePhone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name=\"newsletter\"][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		String actualText=driver.findElement(By.xpath("//div[@id=\"content\"]//h1")).getText();
		Assert.assertTrue(actualText.contains(dataProp.getProperty("registerText")),"Account not created");
		
	}
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithoutFillingAnyDetails() {
		driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
		
		String actualPrivacyPolicyWarning=driver.findElement(By.xpath("//div[contains(@class,\"alert-dismissible\")]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy message not displayed");
	
		String actualFirstName=driver.findElement(By.xpath("//input[@id=\"input-firstname\"]/following-sibling::div")).getText();
		Assert.assertTrue(actualFirstName.contains(dataProp.getProperty("firstNameWarning")),"First Name warning not displayed");
	
		String actualLastName=driver.findElement(By.xpath("//input[@id=\"input-lastname\"]/following-sibling::div")).getText();
		Assert.assertTrue(actualLastName.contains(dataProp.getProperty("lastNameWarning")),"Last Name warning not displayed");
	
		String actualeMail=driver.findElement(By.xpath("//input[@id=\"input-email\"]/following-sibling::div")).getText();
		Assert.assertTrue(actualeMail.contains(dataProp.getProperty("eMailWarning")),"Email warning not displayed");
		
		String actualTelephone=driver.findElement(By.xpath("//input[@id=\"input-telephone\"]/following-sibling::div")).getText();
		Assert.assertTrue(actualTelephone.contains(dataProp.getProperty("telephoneWarning")),"Telephone warning not displayed");
		
		String actualpassword=driver.findElement(By.xpath("//input[@id=\"input-password\"]/following-sibling::div")).getText();
		Assert.assertTrue(actualpassword.contains(dataProp.getProperty("passwordWarning")),"Password warning not displayed");
	}
	
	

}
