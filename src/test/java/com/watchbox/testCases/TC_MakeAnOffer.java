package com.watchbox.testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import com.watchbox.pageObjects.BrandsPage;
import com.watchbox.pageObjects.HomePage;

public class TC_MakeAnOffer extends Base {
	@Test(priority = 1)
	public void navigateToWatchbox() throws Exception {
		HomePage homepage = new HomePage(driver);
		driver.get(baseUrl);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		homepage.clickBrands();
	}

	@Test(priority = 2)
	public void selectBrandAndModel() throws InterruptedException {
		BrandsPage bp = new BrandsPage(driver);
		bp.selectBrands();
		logger.info("Brand Selected");
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2200)");
		// bp.selectModel();
		// logger.info("Model Selected");
		// Thread.sleep(5000);
	}
	/*
	 * @Test(priority=3) public void MakeAnOffer() { ModelPage mp=new
	 * ModelPage(driver); mp.MakeAnOffer();
	 * logger.info("Clicked on Make An Offer Button"); }
	 */
}
