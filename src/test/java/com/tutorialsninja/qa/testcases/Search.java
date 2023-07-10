package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

public class Search extends Base {
	WebDriver driver;
	public Search() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver=initializeBrowser(prop.getProperty("browserName"));
	}
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed(),"Valid product HP not displayed");
	}
	@Test(priority=2)
	public void verifySearchWithInvalidProduct(){
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("inValidProduct"));
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		String actualSearchMessage=driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p")).getText();
		Assert.assertTrue(actualSearchMessage.contains(dataProp.getProperty("noProductText")),"No product message not displayed");
	}
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct(){
		driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		String actualSearchMessage=driver.findElement(By.xpath("//div[@id=\"content\"]/h2/following-sibling::p")).getText();
		Assert.assertTrue(actualSearchMessage.contains(dataProp.getProperty("noProductText")),"No product message not displayed");
		
	}

}
