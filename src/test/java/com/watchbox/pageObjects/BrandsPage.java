package com.watchbox.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrandsPage {

	WebDriver ldriver;

	public BrandsPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(xpath = "//p[text()='Rolex']")
	WebElement brands;

	@FindBy(xpath = "//div//img[@title='Pre-Owned Rolex Cosmograph Daytona 116589RBR']")
	WebElement watchModel;

	public void selectBrands() {
		brands.click();
	}

	public void selectModel() {
		watchModel.click();
	}
}
