package com.watchbox.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ModelPage {

	
	WebDriver ldriver;
	
	public ModelPage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//button[@class='make-an-offer white-bg-btn autoClickMakeAnOffer']")
	WebElement btnMakeAnOffer;
	
	public void MakeAnOffer()
	{
		btnMakeAnOffer.click();
	}

	
}
