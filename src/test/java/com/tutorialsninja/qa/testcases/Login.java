package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
public class Login extends Base {
	WebDriver driver;
	public Login() {
		super();
	}
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
	
	@BeforeMethod
	public void setSite() {
		driver=initializeBrowser(prop.getProperty("browserName"));
		HomePage homepage=new HomePage(driver);
		homepage.clickOnMyAccount();
		homepage.clickOnLogin();
	}
	
	@Test(priority=1)
	public void verifyLoginWithInvalidCrendential() {
		LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAddress(Utilities.generateTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("inValidPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys(Utilities.generateTimeStamp());
		//driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys(dataProp.getProperty("inValidPassword"));
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualLoginMessage=driver.findElement(By.xpath("//div[@id=\"account-login\"]/div[1]")).getText();
		Assert.assertEquals(actualLoginMessage,dataProp.getProperty("LoginMessage"),"No message displayed");
	}
	@Test(priority=2)
	public void verifyLoginWithValidEmailAndValidPassword() {
		LoginPage loginpage=new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@id=\"input-email\"]")).sendKeys(prop.getProperty("validEmail"));
		//driver.findElement(By.xpath("//input[@id=\"input-password\"]")).sendKeys(prop.getProperty("validPassword"));
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualLoginMessage=driver.findElement(By.linkText("Edit your account information")).getText();
		Assert.assertEquals(actualLoginMessage,"Edit your account information","account login success");
	}
	@Test(priority=3)
	public void verifyLoginWithNoEmailAndPassword() { 
		LoginPage loginpage=new LoginPage(driver);
		loginpage.clickOnLoginButton();
		//driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualLoginMessage1=driver.findElement(By.xpath("//div[@id=\"account-login\"]/div[1]")).getText();
		Assert.assertEquals(actualLoginMessage1,dataProp.getProperty("LoginMessage"),"No message displayed");
	}
	
	@DataProvider(name="validCredentialSupplier")
	public Object[][] supplyTestData(){
		Object[][] data=Utilities.getTestDataFromExcel("Login");
		return data;
	}
		
	
	
	
}
