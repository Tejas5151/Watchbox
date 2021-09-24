package WatchboxDemo;

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

public class SellYourWatch {

	static WebDriver driver;
	static WebDriverWait wait=new WebDriverWait(driver, 20);
	static ReadConfig readconfig=new ReadConfig();
	static String webDriver=readconfig.getWebDriver();
	static String chromepath=readconfig.getChromepath();
	static String baseUrl = readconfig.getApplicationUrl();
	static String sfUrl = readconfig.getSFUrl();
	static String username = readconfig.getUsername();
	static String password = readconfig.getPassword();
	static String ExpectedTitle =readconfig.getExpectedTitle();
	static String fisrtName = RandomStringUtils.randomAlphabetic(5);
	static String lastName = RandomStringUtils.randomAlphabetic(5);
	static String emailId=fisrtName+".Test@gmail.com";
	
	public SellYourWatch(WebDriver driver)
	{
		this.driver = driver;
		}
	
	public static void main(String[] args) throws Exception 
	{
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty(webDriver,chromepath);
		driver = new ChromeDriver(options);
		
  	//	SellYourWatch sell=new SellYourWatch(driver);
  		setUp();
   		navigateToSellWatchQuote();
   		sellWatchQuote();
   		verifyOfferSubmittion();
   		loginToSalesforce();
   		verifyAccount();
   		verifyDeal();
  		verifyOriginationsDetails();
  		negotiateOrigination();
  		verifyDealSummary();
  		tearDown();
   		
	}
	
	public static void setUp()
	{
   		driver.manage().window().maximize();        
   		driver.get(baseUrl);
   		implicitWait();
  		String ActualTitle = driver.getTitle();
  		Assert.assertEquals(ExpectedTitle, ActualTitle);
	
	}
	
		public static void navigateToSellWatchQuote() throws Exception
		{
			WebElement cookieButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='affirm consent-btn close-button']")));
			cookieButton.click();
			
			WebElement sellTradeMenu=driver.findElement(By.id("sell-trade"));
	   		JavascriptExecutor js = (JavascriptExecutor) driver;
	   		js.executeScript("arguments[0].click()", sellTradeMenu);
	   		
	   		implicitWait();
	   		WebElement sellTradeMenuItem1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sell-trade']")));
	   		sellTradeMenuItem1.click();

	   		implicitWait();
	   		WebElement sellTradeMenuItem2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='sell-trade']/div/div/div/ul/li[1]/a[@href='/hk/en/sell-your-watch/']")));
	   		sellTradeMenuItem2.click();
	   		
	   		WebElement getMyQuoteButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-receptor='sell']")));
	   		getMyQuoteButton.click();
		
		}
   		public static void sellWatchQuote() throws InterruptedException
   		{
   	   	//CONTACT INFORMATION
   		implicitWait(); 
   		driver.findElement(By.id("first_name")).sendKeys(fisrtName);
   		driver.findElement(By.id("last_name")).sendKeys("Test");
   		driver.findElement(By.id("email-id")).sendKeys(emailId);
   		driver.findElement(By.id("phonenumber")).sendKeys("2225622351");
   		driver.findElement(By.id("contactBtn")).click();
   		//TRADE-IN WATCH DETAILS
   		driver.findElement(By.id("brand")).sendKeys("Rolex");
   		driver.findElement(By.id("model")).sendKeys("AIR-KING");
   		driver.findElement(By.id("ref_no")).sendKeys("05501");
   		driver.findElement(By.id("condition")).sendKeys("Old");
   		driver.findElement(By.id("box")).click();
   		driver.findElement(By.id("papers")).click();
   		driver.findElement(By.id("formattedprice")).sendKeys("8000");
   		driver.findElement(By.id("tradecomment")).sendKeys("Test Comment");
   		implicitWait(); 		
   		
   		Thread.sleep(50000);
   
     	//Captcha Should be Manually Handled	
   		driver.findElement(By.id("sellButton")).click();
   		implicitWait();
   		}
	
		public static void verifyOfferSubmittion()
		{
			WebElement actualSuccessMessage=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"col-12 col-lg-9 col-xl-7 text-center\"]/h1")));
			actualSuccessMessage.getText();
			String expectedSuccessMessage="Quote request submitted";
			System.out.println(actualSuccessMessage);
			Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
		}
	public static void implicitWait()
	{
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	
	public static void loginToSalesforce() throws InterruptedException
	{
		driver.navigate().to(sfUrl);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
		Thread.sleep(15000);
	}
	
	public static void verifyAccount() throws Exception
	{
		
		WebElement searchBox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate' and @type='button']")));
		searchBox.click();
   					
		implicitWait();
		Thread.sleep(10000);
		
		String e1="gnqdn.test@gmail.com";
		WebElement searchString=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search' and @class='slds-input']")));
		searchString.sendKeys(e1);
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

		WebElement accPhoneNumber=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='+91 2225622351']")));
		String accPhoneNumberText=accPhoneNumber.getText();
		System.out.println("Phone Number: "+accPhoneNumberText);

	}
	public static void verifyDeal() throws InterruptedException
	{
   		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		

		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@class='slds-card__header-link baseCard__header-title-container']/span[@title='Deals']")).click();
					
		Thread.sleep(5000);
		WebElement table = driver.findElement(By.xpath("//table[@role='grid']"));		
		WebElement tableRow=table.findElement(By.xpath("//table[@role='grid']/tbody/tr[1]/th/span/a"));			
		System.out.println("Deal :"+tableRow.getText()+" Opened");
		tableRow.click();
		
		
		Thread.sleep(5000);

		System.out.println("Deal Name: "+driver.findElement(By.xpath("//lightning-formatted-name[@data-output-element-id='output-field']")).getText());

		System.out.println("Account Name: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Account Name']/parent::div/p[2]/slot/force-lookup/div/force-hoverable-link/div/a/slot/slot/span")).getText());
		
		System.out.println("Customer Email: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Email']/parent::div//a")).getText());
		
		System.out.println("Customer Phone: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Phone']/parent::div//lightning-formatted-text")).getText());
		
	    System.out.println("Deal Type: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Deal Type']/parent::div//lightning-formatted-text")).getText());
	    Thread.sleep(5000);
	}	    
	
    public static void verifyOriginationDetails()
    {
    System.out.println("Deal "+driver.findElement(By.xpath("//a[@title='Sales']/span[contains(text(),'Originations')]")).getText());
    
	System.out.println("Watch Title: "+driver.findElement(By.xpath("//flexipage-component2[2]//span[@class='watchtitle']")).getText());
    
	System.out.println("Customer Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Customer']//following::div[1]/lightning-formatted-number")).getText());
	
	System.out.println("Original Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Customer']//following::div[2]/lightning-formatted-number")).getText());
	
	System.out.println("Company Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Company']//following::div[1]/lightning-formatted-number")).getText());
	
     
}
	public static void negotiateOrigination() throws InterruptedException
	{	
		//Negotiate
		WebElement showMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='slds-button slds-button_icon-border-filled slds-button_icon-small']")));
		showMenu.click();
		
		WebElement negotiateMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@role='menu']//span[text()='Negotiate']")));
		negotiateMenu.click();
		
		WebElement companyOffer=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='CompanyOfferAmount__c']")));
		companyOffer.sendKeys("92950");
		
		WebElement saveButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-modal__container']//button[@title='Save']")));
		saveButton.click();
		
		implicitWait();
		System.out.println("Company Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Company']//following::div[1]/lightning-formatted-number")).getText());
		
	}
	public static void verifyDealSummary() throws InterruptedException
	{
		
   		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		
		implicitWait();
		String originationsTotal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//flexipage-component2[@data-component-id='WB_DealSummary']//div[@class='cWB_WatchOriginationSummary']/div[2]/b/lightning-formatted-number"))).getText();
		//	originationsTotal.getText();	
			
			String estimatedTax=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[14]/div[4]/div/lightning-formatted-number"))).getText();
		//	estimatedTax.getText();
			
			String watchCarePlan=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[16]/div[3]/div/lightning-formatted-number"))).getText();
		//	watchCarePlan.getText();
			
			String serviceCharges=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[18]/div[3]/div/lightning-formatted-number"))).getText();
		//	serviceCharges.getText();
			
			
		
			int ototal=Integer.parseInt(originationsTotal);

			System.out.println("Deal Subtotal: "+ototal);
	}
	public static void tearDown()
	{
		driver.close();
	}
	
}
	
