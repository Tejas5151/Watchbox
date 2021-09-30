package WatchboxDemo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.watchbox.utilities.ReadConfig;

public class MakeAnOffer {

	static WebDriver driver;
	WebDriverWait wait=new WebDriverWait(driver, 20);
	static ReadConfig readconfig=new ReadConfig();
	static String webDriver=readconfig.getWebDriver();
	static String chromepath=readconfig.getChromepath();
	static String baseUrl = readconfig.getApplicationUrl();
	static String sfUrl = readconfig.getSFUrl();
	static String username = readconfig.getUsername();
	static String password = readconfig.getPassword();
	static String ExpectedTitle =readconfig.getExpectedTitle();
	static String fisrtName = RandomStringUtils.randomAlphabetic(5);
	String lastName = RandomStringUtils.randomAlphabetic(5);
	static String accName;
	static String watchTitle;
	static String emailId=fisrtName+".Test@gmail.com";
	
		public MakeAnOffer(WebDriver driver)
	{
		this.driver = driver;
		}
	
	public static void main(String[] args) throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty(webDriver,chromepath);
		driver = new ChromeDriver(options);
			
		
		MakeAnOffer mknoffer=new MakeAnOffer(driver);
		mknoffer.setUp();
		mknoffer.navigateToWatchDetails();
	/*	mknoffer.submitOffer();
		mknoffer.verifyOfferSubmittion();
		mknoffer.loginToSalesforce();
		mknoffer.verifyAccount();
		mknoffer.verifyDeal();
		mknoffer.verifySalesDetails();
		mknoffer.negotiateSales();
		mknoffer.verifyDealSummary();
		mknoffer.verifyOfferOnWBX();
		mknoffer.tearDown();
		*/
   		}
	
	public void setUp()
	{

		driver.manage().window().maximize();     
		driver.get(baseUrl);
		implicitWait();
		String ActualTitle = driver.getTitle();
		Assert.assertEquals(ExpectedTitle, ActualTitle);
	
	}
	public void navigateToWatchDetails() throws InterruptedException
		{
		WebElement cookieButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='affirm consent-btn close-button']")));
		cookieButton.click();
   		
		WebElement brandMenu=driver.findElement(By.xpath("//div[@class='menu-group']//li[@id='shop']//a[text()='BRANDS']"));
   		JavascriptExecutor js = (JavascriptExecutor) driver;
   		js.executeScript("arguments[0].click()", brandMenu);
   				
   		WebElement watchBrand=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Rolex']")));
   		watchBrand.click();
   		   		
   		WebElement watchFamily=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='GMT-Master II']")));
   		watchFamily.click();
   		  	
   		implicitWait();
  		js.executeScript("window.scrollBy(0,4400)");
		implicitWait();
		
   		//WebElement watch=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='https://watchbox-cdn.imgix.net/posted-product-images/637666106718156028.jpg?w=1184&h=1184']")));
   		//watch.click();

		
List<WebElement> allWatches = driver.findElements(By.xpath("//*[@id=\"grid-main-container\"]/div"));
		
		for( WebElement watch : allWatches){
			
			System.out.println(watch.getText());
			System.out.println(watch);
			System.out.println("*********************************************************************");
		}
		
		/*Boolean isText=driver.findElement(By.xpath("//div[text()='Continue to the United States site']")).isDisplayed();
   		 if(isText==true)
   		 {
   			driver.findElement(By.xpath("//div[text()='Continue to the United States site']")).click();
   		    		 }*/

  // 		implicitWait();
   //		WebElement mknofferButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='make-an-offers']")));
   //		mknofferButton.click();  	

		}

		public void submitOffer() throws Exception
		{
			implicitWait();
   			driver.findElement(By.id("first_name")).sendKeys(fisrtName);
			driver.findElement(By.id("last_name")).sendKeys("Test");
			driver.findElement(By.id("email-id")).sendKeys(emailId);
			driver.findElement(By.id("phonenumber")).sendKeys("5552221245");
			driver.findElement(By.id("offerPrice")).sendKeys("92950");
			driver.findElement(By.id("Shipping_Country")).sendKeys("United States");
	   		//driver.findElement(By.id("postalCode")).sendKeys("11111");

	   		WebElement submitButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='submit_btn_makeAnOffer' and @name='submit']")));
	   		submitButton.click();
		
		}
		
		public void verifyOfferSubmittion() throws InterruptedException
		{
			Thread.sleep(5000);
			String actualSuccessMessage=driver.findElement(By.xpath("//div[@class='heading']")).getText();
			String expectedSuccessMessage="Your offer has been received!";
			System.out.println(actualSuccessMessage);
	  		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
	  			}
		
		public void implicitWait()
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		
		public void loginToSalesforce() throws InterruptedException
		{
			driver.navigate().to(sfUrl);
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("Login")).click();
			Thread.sleep(15000);
		}
		
		public void verifyAccount() throws Exception
		{
			WebElement searchBox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate' and @type='button']")));
			searchBox.click();
	   					
			implicitWait();
			Thread.sleep(5000);
			
			WebElement searchString=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search' and @class='slds-input']")));
			searchString.sendKeys(emailId);
			searchString.sendKeys(Keys.ENTER);
					
			Thread.sleep(8000);
			WebElement accTable = driver.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][1]//table[@role='grid']"));
			
			//Account verification
			WebElement accTableRow=accTable.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][1]//table[@role='grid']/tbody/tr/th/span/a"));
			String accRowText=accTableRow.getText();
			System.out.println("Account Created: "+accRowText);
			
			//Deal verification
			WebElement dealTable = driver.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][2]//table[@role='grid']"));
			WebElement dealTableRow=dealTable.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][2]//table[@role='grid']/tbody/tr/th/span/a"));
			String dealRowText=dealTableRow.getText();
			System.out.println("Deal Created: "+dealRowText);
			
			
			accTableRow.click();
			
			Thread.sleep(5000);
			
			WebElement accEmail=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='emailuiFormattedEmail']")));
			String accEmailText=accEmail.getText();
			System.out.println("Email: "+accEmailText);

			WebElement accPhoneNumber=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='+91 5552221245']")));
			String accPhoneNumberText=accPhoneNumber.getText();
			System.out.println("Phone Number: "+accPhoneNumberText);
			
		}
		
			public void verifyDeal() throws InterruptedException
		{
	   		JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,450)");
			
			Thread.sleep(3000);
			driver.findElement(By.xpath("//a[@class='slds-card__header-link baseCard__header-title-container']/span[@title='Deals']")).click();
						
			Thread.sleep(5000);
			WebElement table = driver.findElement(By.xpath("//table[@role='grid']"));		
			WebElement tableRow=table.findElement(By.xpath("//table[@role='grid']/tbody/tr[1]/th/span/a"));			
			System.out.println("Deal :"+tableRow.getText()+"Opened");
			tableRow.click();
			
			
			Thread.sleep(5000);

			System.out.println("Deal Name: "+driver.findElement(By.xpath("//lightning-formatted-name[@data-output-element-id='output-field']")).getText());

		    accName=driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Account Name']/parent::div/p[2]/slot/force-lookup/div/force-hoverable-link/div/a/slot/slot/span")).getText();
			System.out.println("Account Name: "+accName);
			
			System.out.println("Customer Email: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Email']/parent::div//a")).getText());
			
			System.out.println("Customer Phone: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Phone']/parent::div//lightning-formatted-text")).getText());
			
		    System.out.println("Deal Type: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Deal Type']/parent::div//lightning-formatted-text")).getText());
			
		//	System.out.println("Marketing Source: "+driver.findElement(By.xpath("//*[@id='window']")).getText());
			
		}
			
		    public void verifySalesDetails()
		    {
			    System.out.println("Deal "+driver.findElement(By.xpath("//a[@title='Sales']/span[contains(text(),'Sales')]")).getText());
			    
			    watchTitle=driver.findElement(By.xpath("//span[@class='watchtitle']")).getText();
				System.out.println("Watch Title: "+watchTitle);
			    
				System.out.println("Customer Offer: "+driver.findElement(By.xpath("//flexipage-component2[1]//div[text()='Customer']//following::div[1]/lightning-formatted-number")).getText());
				
				System.out.println("Original Offer: "+driver.findElement(By.xpath("//flexipage-component2[1]//div[text()='Customer']//following::div[2]/lightning-formatted-number")).getText());
				
				System.out.println("Company Offer: "+driver.findElement(By.xpath("//flexipage-component2[1]//div[text()='Company']//following::div[1]/lightning-formatted-number")).getText());	
		    }
	    
		    
			public void negotiateSales()
			{	
				WebElement showMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_icon-border-filled slds-button_icon-small']")));
				showMenu.click();
				
				WebElement negotiateMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='menu']//span[text()='Negotiate']")));
				negotiateMenu.click();
				
				WebElement companyOffer=wait.until(ExpectedConditions.elementToBeClickable(By.name("CompanyOfferAmount__c")));
				companyOffer.sendKeys("92950");
						
				WebElement saveButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-modal__container']//button[@title='Save']")));
				saveButton.click();
				
				WebElement salesTotal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[@data-component-id='WB_DealSummary']//div[@class='cWB_WatchSaleSummary']/div[2]/b/lightning-formatted-number")));
				salesTotal.getText();
				
				driver.navigate().refresh();
				
			}	    

			public void verifyDealSummary() throws InterruptedException
			{
				
		   		JavascriptExecutor js = (JavascriptExecutor) driver;
		   		
				js.executeScript("window.scrollBy(0,450)");
				
				implicitWait();
				String salesTotal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[@data-component-id='WB_DealSummary']//div[@class='cWB_WatchSaleSummary']/div[2]/b/lightning-formatted-number"))).getText();
				//	salesTotal.getText();	
					
					String estimatedTax=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[7]/div[4]/div/lightning-formatted-number"))).getText();
				//	estimatedTax.getText();
					
					String watchCarePlan=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[9]/div[3]/div/lightning-formatted-number"))).getText();
				//	watchCarePlan.getText();
					
					String serviceCharges=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[11]/div[3]/div/lightning-formatted-number"))).getText();
				//	serviceCharges.getText();
					
					String actualDealSubtatal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[14]/div[3]/div/lightning-formatted-number"))).getText();
					
				
					int stotal=Integer.parseInt(salesTotal);
					int et=Integer.parseInt(estimatedTax);
					int wcp=Integer.parseInt(watchCarePlan);
					int sc=Integer.parseInt(serviceCharges);
					
					int dealSubTotal=stotal+et+wcp+sc;
					System.out.println("Deal Subtotal: "+dealSubTotal);
					Assert.assertEquals(actualDealSubtatal, dealSubTotal);
					
			}
			
			public void verifyOfferOnWBX()
			{
				driver.findElement(By.xpath("//flexipage-component2[1]//span[text()='WBX Linked']")).click();
				
				WebElement wt=driver.findElement(By.xpath("//div[@class='slds-grid primaryFieldRow']//lightning-formatted-text"));
				wt.getText();
				Assert.assertEquals(wt, watchTitle);
				
				
			//Market Prize Details
				WebElement salesOfferInfoTable = driver.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table[@role='grid']"));
				
				//Account verification
				String saleDate=salesOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[1]/lightning-primitive-cell-factory/span/div/lightning-formatted-date-time")).getText();
				System.out.println("Sales offer Information: "+saleDate);
				
				String saleStatus=salesOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[2]/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")).getText();
				System.out.println("Sales offer Information: "+saleStatus);
								
				String saleTrader=salesOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[3]/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")).getText();
				System.out.println("Sales offer Information: "+saleTrader);
				
				String saleWatchboxOffer=salesOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[4]/lightning-primitive-cell-factory/span/div/lightning-formatted-number")).getText();
				System.out.println("Sales offer Information: "+saleWatchboxOffer);
				
				String saleClientOffer=salesOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[5]/lightning-primitive-cell-factory/span/div/lightning-formatted-number")).getText();
				System.out.println("Sales offer Information: "+saleClientOffer);
				

			}

			
		
		public void tearDown()
		{
			driver.close();
		}
		
}