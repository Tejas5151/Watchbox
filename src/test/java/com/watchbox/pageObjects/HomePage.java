package com.watchbox.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver ldriver;
	
	public HomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath="//a[text()='BRANDS' and @tabindex='3']")
	WebElement btnBrands;

	@FindBy(xpath="//*[@class='affirm consent-btn close-button']")
	WebElement cookieMsgClose;
	
	public void clickBrands() throws Exception
	{
		
		
		//clickClose();
	/*	if(ldriver.getTitle().equals("Pre-Owned Luxury Watches Online | Buy, Sell, Trade Rolex Patek Panerai | TheWatchBox.com | Salesforce Commerce Cloud | 3.3.0"))
		{
			Assert.assertTrue(true);
		//	logger.info("Watcbox Homepage URL Successfully Opened");
		}
		else
		{
			Assert.assertTrue(false);
			//logger.info("Watchbox Homepage URL Failed to Opened");
		}
		Thread.sleep(3000);
		*/
		btnBrands.click();
		//logger.info("Clicked on Brands Menu");
		Thread.sleep(3000);
	}

	public void clickClose()
	{
		cookieMsgClose.click();
	}
}
