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
	static String username = readconfig.getUsername();
	static String password = readconfig.getPassword();
	static String ExpectedTitle =readconfig.getExpectedTitle();
	String fisrtName = RandomStringUtils.randomAlphabetic(5);
	String lastName = RandomStringUtils.randomAlphabetic(5);
	String emailId=fisrtName+".Test@gmail.com";
	
	public TradeYourWatch(WebDriver driver)
	{
		this.driver = driver;
		}
	
	public static void main(String[] args) throws Exception {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty(webDriver,chromepath);
		driver= new ChromeDriver();
		driver.manage().window().maximize();        
   		driver.get(baseUrl);
   		implicitWait();
  		String ActualTitle = driver.getTitle();
  		Assert.assertEquals(ExpectedTitle, ActualTitle);
   		
  		TradeYourWatch trade=new TradeYourWatch(driver);
		trade.navigateToTradeWatchQuote();
		trade.tradeQuote();  
		trade.verifyOfferSubmittion();
		trade.loginToSalesforce();
		trade.verifyAccount();
		Thread.sleep(5000);
	}

	public void navigateToTradeWatchQuote()
	{
		driver.findElement(By.xpath("//*[@class='affirm consent-btn close-button']")).click();
		implicitWait();
		driver.findElement(By.id("sell-trade")).click();
		implicitWait();;
		driver.findElement(By.xpath("//li[@id='sell-trade']/div/div/div/ul/li/a[@href=\"/hk/en/trade-watch/\"]")).click(); 	
		implicitWait();;
		driver.findElement(By.xpath("//a[@href=\"/hk/en/trade-watch-quote/\" and text()='Get My Quote']")).click();
		implicitWait();
	}
	public void tradeQuote() throws Exception
	{
   		//CONTACT INFORMATION
   		driver.findElement(By.id("first_name")).sendKeys(fisrtName);
   		driver.findElement(By.id("last_name")).sendKeys("Test");
   		driver.findElement(By.id("email-id")).sendKeys(emailId);
   		driver.findElement(By.id("phonenumber")).sendKeys("2225622351");
   		
   		//WHAT WATCH ARE YOU LOOKING FOR?
   		driver.findElement(By.id("lookupbrand")).sendKeys("Rolex");
   		driver.findElement(By.id("brandmodel")).sendKeys("DAY-DATE");
   		driver.findElement(By.id("reference_no")).sendKeys("118238");
   		driver.findElement(By.id("lookUpComments")).sendKeys("Test Comment");
   		
   		//TRADE-IN WATCH DETAILS
   		driver.findElement(By.id("brand")).sendKeys("B Swiss");
   		driver.findElement(By.id("model")).sendKeys("PRESTIGE");
   		driver.findElement(By.id("ref_no")).sendKeys("00.50506.08.13.01");
   		driver.findElement(By.id("condition")).sendKeys("Mint Condition");
   		driver.findElement(By.xpath("//button[text()='Box only']")).click();
   		driver.findElement(By.id("formattedprice")).sendKeys("7000");
   		driver.findElement(By.id("tradecomment")).sendKeys("Test Comment");
   		implicitWait();
   		
   		Thread.sleep(8000);
   		//driver.findElement(By.id("//div[@class='recaptcha-checkbox-border']")).click();
   		//implicitWait();
   		driver.findElement(By.id("sellButton")).click();
	}
	
	public void verifyOfferSubmittion()
	{
		String actualSuccessMessage=driver.findElement(By.xpath("//div[@class=\"col-12 col-lg-9 col-xl-7 text-center\"]/h1")).getText();
		String expectedSuccessMessage="Quote request submitted";
		System.out.println(actualSuccessMessage);
  		Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage);
	}
	
	public static void implicitWait()
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
		WebElement accTable = driver.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][2]//table[@role='grid']"));
		
		//Account verification
		WebElement accTableRow=accTable.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][2]//table[@role='grid']/tbody/tr/th/span/a"));
		String accRowText=accTableRow.getText();
		System.out.println("Account Created: "+accRowText);
		
		//Deal verification
		WebElement dealTable = driver.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][1]//table[@role='grid']"));
		WebElement dealTableRow=dealTable.findElement(By.xpath("//div[@class='resultsItem slds-col slds-no-flex slds-m-bottom_small'][1]//table[@role='grid']/tbody/tr/th/span/a"));
		String dealRowText=dealTableRow.getText();
		System.out.println("Deal Created: "+dealRowText);
		
		
		accTableRow.click();
		
		Thread.sleep(5000);
		
		WebElement accEmail=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='emailuiFormattedEmail']")));
		String accEmailText=accEmail.getText();
		System.out.println("Email: "+accEmailText);

		WebElement accPhoneNumber=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='+1 2225622351']")));
		String accPhoneNumberText=accPhoneNumber.getText();
		System.out.println("Phone Number: "+accPhoneNumberText);

	}
	public void verifyDeal() throws InterruptedException
	{
   		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		

		driver.findElement(By.xpath("//a[@class='slds-card__header-link baseCard__header-title-container']/span[@title='Deals']")).click();
					
		Thread.sleep(5000);
		WebElement table = driver.findElement(By.xpath("//table[@role='grid']"));		
		WebElement tableRow=table.findElement(By.xpath("//table[@role='grid']/tbody/tr[1]/th/span/a"));			
		System.out.println("Deal :"+tableRow.getText()+"Opened");
		tableRow.click();
		
		
		Thread.sleep(5000);

		System.out.println("Deal Name: "+driver.findElement(By.xpath("//lightning-formatted-name[@data-output-element-id='output-field']")).getText());

		System.out.println("Account Name: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Account Name']/parent::div/p[2]/slot/force-lookup/div/force-hoverable-link/div/a/slot/slot/span")).getText());
		
		System.out.println("Customer Email: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Email']/parent::div//a")).getText());
		
		System.out.println("Customer Phone: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Phone']/parent::div//lightning-formatted-text")).getText());
		
	    System.out.println("Deal Type: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Deal Type']/parent::div//lightning-formatted-text")).getText());
		
		
		
	}
}
