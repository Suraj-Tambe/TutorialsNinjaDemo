package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(xpath="//input[@id=\"input-email\"]")
	WebElement emailAddressField;
	
	@FindBy(xpath="//input[@id=\"input-password\"]")
	WebElement passwordField;

	@FindBy(xpath="//input[@value=\"Login\"]")
	WebElement loginButton;

		
	public LoginPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String textEmail) {
		emailAddressField.sendKeys(textEmail);
	}
	public void enterPassword(String Pass) {
		passwordField.sendKeys(Pass);
	}
	public void clickOnLoginButton() {
		loginButton.click();
	}
}
