package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	@FindBy(linkText="My Account")
	WebElement myAccountDropMenu;
	
	@FindBy(linkText="Login")
	WebElement loginOption;
	
	public HomePage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	public void clickOnLogin() {
		loginOption.click();
	}
}
