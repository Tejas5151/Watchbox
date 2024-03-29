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

public class TradeYourWatch {

	
	static WebDriver driver;
	WebDriverWait wait=new WebDriverWait(driver, 20);
	static ReadConfig readconfig=new ReadConfig();
	static String webDriver=readconfig.getWebDriver();
	static String chromepath=readconfig.getChromepath();
	static String baseUrl = readconfig.getApplicationUrl();
	static String sfUrl = readconfig.getSFUrl();
	static String location=readconfig.getLocation();
	static String username = readconfig.getUsername();
	static String password = readconfig.getPassword();
	static String ExpectedTitle =readconfig.getExpectedTitle();
	static String fisrtName = RandomStringUtils.randomAlphabetic(5);
//	String lastName = RandomStringUtils.randomAlphabetic(5);
	static String emailId=fisrtName+".Test@gmail.com";
	static String accName;
	static String salesWatchTitle;
	static String originationWatchTitle;
	
	public TradeYourWatch(WebDriver driver)
	{
		this.driver = driver;
		}
	
	public static void main(String[] args) throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty(webDriver,chromepath);
		driver= new ChromeDriver();

   		
  		TradeYourWatch trade=new TradeYourWatch(driver);
  		trade.setUp();
  		trade.loactionSetting();
  		trade.navigateToTradeWatchQuote();
  		trade.tradeQuote();  
  		trade.verifyOfferSubmittion();
  		trade.loginToSalesforce();
  		trade.verifyAccount();
  		trade.verifyDeal();
  		trade.verifySalesDetails();
  		trade.verifyOriginationDetails();
  		trade.negotiateSales();
  		trade.negotiateOrigination();
  		trade.verifyDealSummary();
		trade.verifyOfferOnWBX();
		trade.tearDown();
	}
	
	public void setUp()
	{
   		driver.manage().window().maximize();        
   		driver.get(baseUrl);
   		implicitWait();
  		String ActualTitle = driver.getTitle();
  		Assert.assertEquals(ExpectedTitle, ActualTitle);
	
	}
	public void loactionSetting() throws Exception
	{
		driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div[3]/div/div[1]/div/div/nav/div/div[1]/div[1]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("country")).sendKeys(location);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@value='Confirm']")).click();
	}
	
	public void navigateToTradeWatchQuote()
	{
		WebElement cookieButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='affirm consent-btn close-button']")));
		cookieButton.click();
		
   		implicitWait();
   		WebElement sellTradeMenuItem1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sell-trade']")));
   		sellTradeMenuItem1.click();
   		
   		implicitWait();
   		WebElement sellTradeMenuItem2=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='sell-trade']/div/div/div/ul/li/a[@href='/hk/en/trade-watch/']")));
   		sellTradeMenuItem2.click();
   		
		driver.findElement(By.xpath("//a[@data-receptor='trade']")).click();
		implicitWait();
	}
	public void tradeQuote() throws Exception
	{
   		//CONTACT INFORMATION
		implicitWait();
   		driver.findElement(By.id("first_name")).sendKeys(fisrtName);
   		driver.findElement(By.id("last_name")).sendKeys("Test");
   		driver.findElement(By.id("email-id")).sendKeys(emailId);
   		driver.findElement(By.id("phonenumber")).sendKeys("2225622351");
   		implicitWait();
   		driver.findElement(By.id("contactBtn")).click();
   		
   		//WHAT WATCH ARE YOU LOOKING FOR?
   		driver.findElement(By.id("lookupbrand")).sendKeys("Rolex");
   		driver.findElement(By.id("brandmodel")).sendKeys("DAY-DATE");
   		driver.findElement(By.id("reference_no")).sendKeys("118238");
   		driver.findElement(By.id("lookUpComments")).sendKeys("Test Comment");
   		driver.findElement(By.xpath("//button[text()='Next: Trading In']")).click();
   		//TRADE-IN WATCH DETAILS
   		driver.findElement(By.id("brand")).sendKeys("B Swiss");
   		driver.findElement(By.id("model")).sendKeys("PRESTIGE");
   		driver.findElement(By.id("ref_no")).sendKeys("00.50506.08.13.01");
   		driver.findElement(By.id("condition")).sendKeys("Mint Condition");
   		driver.findElement(By.id("box")).click();
   		driver.findElement(By.id("papers")).click();
   		driver.findElement(By.id("formattedprice")).sendKeys("7000");
   		driver.findElement(By.id("tradecomment")).sendKeys("Test Comment");
   		implicitWait();
   		
   		Thread.sleep(30000);

   		driver.findElement(By.id("sellButton")).click();
   		implicitWait();
	}
	
	public void verifyOfferSubmittion()
	{
		String actualSuccessMessage=driver.findElement(By.xpath("//div[@class=\"col-12 col-lg-9 col-xl-7 text-center\"]/h1")).getText();
		String expectedSuccessMessage="Quote request submitted";
		System.out.println(actualSuccessMessage);
		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
	}
	
	public void implicitWait()
	{
	driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
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
		Thread.sleep(10000);
		
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

		WebElement accPhoneNumber=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='+91 2225622351']")));
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
	
    public void verifySalesDetails()
    {
	    System.out.println("Deal "+driver.findElement(By.xpath("//a[@title='Sales']/span[contains(text(),'Sales')]")).getText());
	    
	    salesWatchTitle=driver.findElement(By.xpath("//flexipage-component2[1]//span[@class='watchtitle']")).getText();
		System.out.println("Watch Title: "+salesWatchTitle);
	    
		System.out.println("Customer Offer: "+driver.findElement(By.xpath("//flexipage-component2[1]//div[text()='Customer']//following::div[1]/lightning-formatted-number")).getText());
		
		System.out.println("Original Offer: "+driver.findElement(By.xpath("//flexipage-component2[1]//div[text()='Customer']//following::div[2]/lightning-formatted-number")).getText());
		
		System.out.println("Company Offer: "+driver.findElement(By.xpath("//flexipage-component2[1]//div[text()='Company']//following::div[1]/lightning-formatted-number")).getText());	
    }
    
	public void negotiateSales()
	{	
		WebElement showMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[2]//button[@class='slds-button slds-button_icon-border-filled slds-button_icon-small']")));
		showMenu.click();
		
		WebElement negotiateMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[2]//div[@role='menu']//span[text()='Negotiate']")));
		negotiateMenu.click();
		
		WebElement companyOffer=wait.until(ExpectedConditions.elementToBeClickable(By.name("CompanyOfferAmount__c")));
		companyOffer.sendKeys("92950");
		
		WebElement customerOffer=wait.until(ExpectedConditions.elementToBeClickable(By.name("CustomerOfferAmount__c")));
		customerOffer.sendKeys("92950");
		
		WebElement saveButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-modal__container']//button[@title='Save']")));
		saveButton.click();
		
		driver.navigate().refresh();
	}
    
	    public void verifyOriginationDetails()
	    {
	    System.out.println("Deal "+driver.findElement(By.xpath("//a[@title='Sales']/span[contains(text(),'Originations')]")).getText());
	    
	    originationWatchTitle=driver.findElement(By.xpath("//flexipage-component2[2]//span[@class='watchtitle']")).getText();
		System.out.println("Watch Title: "+originationWatchTitle);
	    
		System.out.println("Customer Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Customer']//following::div[1]/lightning-formatted-number")).getText());
		
		System.out.println("Original Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Customer']//following::div[2]/lightning-formatted-number")).getText());
		
		System.out.println("Company Offer: "+driver.findElement(By.xpath("//flexipage-component2[2]//div[text()='Company']//following::div[1]/lightning-formatted-number")).getText());
		
	     
	}
	
	public void negotiateOrigination()
	{	
		//Negotiate
		WebElement showMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[2]//button[@class='slds-button slds-button_icon-border-filled slds-button_icon-small']")));
		showMenu.click();
		
		WebElement negotiateMenu=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[2]//div[@role='menu']//span[text()='Negotiate']")));
		negotiateMenu.click();
		
		WebElement companyOffer=wait.until(ExpectedConditions.elementToBeClickable(By.name("CompanyOfferAmount__c")));
		companyOffer.sendKeys("92950");
		
		WebElement saveButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='slds-modal__container']//button[@title='Save']")));
		saveButton.click();
		
		driver.navigate().refresh();
					
	}
	
	public void verifyDealSummary() throws InterruptedException
	{
		
   		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		
		String salesTotal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[@data-component-id='WB_DealSummary']//div[@class='cWB_WatchSaleSummary']/div[2]/b/lightning-formatted-number"))).getText();
	//	salesTotal.getText();
		
		String originationsTotal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//flexipage-component2[@data-component-id='WB_DealSummary']//div[@class='cWB_WatchOriginationSummary']/div[2]/b/lightning-formatted-number"))).getText();
	//	originationsTotal.getText();	
		
		String estimatedTax=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[14]/div[4]/div/lightning-formatted-number"))).getText();
	//	estimatedTax.getText();
		
		String watchCarePlan=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[16]/div[3]/div/lightning-formatted-number"))).getText();
	//	watchCarePlan.getText();
		
		String serviceCharges=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[18]/div[3]/div/lightning-formatted-number"))).getText();
	//	serviceCharges.getText();
		
		String actualDealSubtatal=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='brandBand_2']//div[@class='forceRelatedListContainer cWB_DealSummary']/article/div[2]/div[19]/div[3]/div/lightning-formatted-number"))).getText();
		
		int stotal=Integer.parseInt(salesTotal);
		int ototal=Integer.parseInt(originationsTotal);
		int et=Integer.parseInt(estimatedTax);
		int wcp=Integer.parseInt(watchCarePlan);
		int sc=Integer.parseInt(serviceCharges);
		
		int dTotal=(stotal - ototal)+et+wcp+sc;
		System.out.println("Deal Subtotal: "+dTotal);
		Assert.assertEquals(actualDealSubtatal, dTotal);
		
	}
	
	public void verifyOfferOnWBX()
	{
		driver.findElement(By.xpath("//flexipage-component2[1]//span[text()='WBX Linked']")).click();
		
		WebElement swt=driver.findElement(By.xpath("//div[@class='slds-grid primaryFieldRow']//lightning-formatted-text"));
		swt.getText();
		Assert.assertEquals(swt, salesWatchTitle);
		
		
	//Market Prize Sale Offer Information
		WebElement salesOfferInfoTable = driver.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table[@role='grid']"));
		

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
		
		driver.navigate().back();
		
		driver.findElement(By.xpath("//flexipage-component2[2]//span[text()='WBX Linked']")).click();
		
		WebElement owt=driver.findElement(By.xpath("//div[@class='slds-grid primaryFieldRow']//lightning-formatted-text"));
		owt.getText();
		Assert.assertEquals(owt, originationWatchTitle);
		
		
	//Market Prize Origination Offer Information
		WebElement originationOfferInfoTable = driver.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_originOfferInfoWrapper']//table[@role='grid']"));
		
		//Account verification
		String originationDate=originationOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_originOfferInfoWrapper']//table/tbody/tr[1]/td[1]/lightning-primitive-cell-factory/span/div/lightning-formatted-date-time")).getText();
		System.out.println("Sales offer Information: "+originationDate);
		
		String originationStatus=originationOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[2]/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")).getText();
		System.out.println("Sales offer Information: "+originationStatus);
						
		String originationTrader=originationOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[3]/lightning-primitive-cell-factory/span/div/lightning-base-formatted-text")).getText();
		System.out.println("Sales offer Information: "+originationTrader);
		
		String originationWatchboxOffer=originationOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[4]/lightning-primitive-cell-factory/span/div/lightning-formatted-number")).getText();
		System.out.println("Sales offer Information: "+originationWatchboxOffer);
		
		String originationClientOffer=originationOfferInfoTable.findElement(By.xpath("//flexipage-component2[@data-component-id='wb_salesOfferInfoWrapper']//table/tbody/tr[1]/td[5]/lightning-primitive-cell-factory/span/div/lightning-formatted-number")).getText();
		System.out.println("Sales offer Information: "+originationClientOffer);

	}
	public void tearDown()
	{
		driver.close();
	}
}
