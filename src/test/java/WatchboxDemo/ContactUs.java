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

public class ContactUs {

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
	
		public ContactUs(WebDriver driver)
	{
		this.driver = driver;
		}
	
	public static void main(String[] args) throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty(webDriver,chromepath);
		driver = new ChromeDriver(options);
		
		//ContactUs cu=new ContactUs(driver);
		setUp();
		navigateToContactUs();
		submitMessage();
		contactMessageConfirmation();
		loginToSalesforce();
		verifyAccount();
		verifyDeal();
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
	
	public static void navigateToContactUs() throws InterruptedException
	{
		WebElement cookieButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='affirm consent-btn close-button']")));
		cookieButton.click();
		
		Thread.sleep(3000);
		WebElement contactUsEmail=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"footer-main\"]//li[@class='contactUS-email']")));
		contactUsEmail.click();
			
	}
	
	public static void submitMessage() throws InterruptedException
	{
		driver.findElement(By.id("first_name")).sendKeys(fisrtName);
		driver.findElement(By.id("last_name")).sendKeys("Test");
		driver.findElement(By.id("email-id")).sendKeys(emailId);
		driver.findElement(By.id("phonenumber")).sendKeys("5552221245");
		driver.findElement(By.id("country_select")).sendKeys("United States");
		implicitWait();
		driver.findElement(By.id("postalCode")).sendKeys("11111");
		driver.findElement(By.id("subject")).sendKeys("Buy from WatchBox");
		driver.findElement(By.id("comments")).sendKeys("Test Comment");
		Thread.sleep(25000);
		driver.findElement(By.id("contactusButton")).click();
		
	}
	public static void implicitWait()
	{
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	
	public static void contactMessageConfirmation() throws InterruptedException
	{
		Thread.sleep(5000);
		String actualSuccessMessage=driver.findElement(By.xpath("//div[@class='contact-confirmation-page']/div/div/span")).getText();
		String expectedSuccessMessage="We’ve received your message";
		System.out.println(actualSuccessMessage);
  		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);
  		Thread.sleep(3000);
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
		Thread.sleep(5000);
		
		WebElement searchString=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='search' and @class='slds-input']")));
		String e1="IGtml.Test@gmail.com";
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

		WebElement accPhoneNumber=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='+91 5552221245']")));
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
		System.out.println("Deal :"+tableRow.getText()+"Opened");
		tableRow.click();
		
		
		Thread.sleep(5000);

		System.out.println("Deal Name: "+driver.findElement(By.xpath("//lightning-formatted-name[@data-output-element-id='output-field']")).getText());

		System.out.println("Account Name: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Account Name']/parent::div/p[2]/slot/force-lookup/div/force-hoverable-link/div/a/slot/slot/span")).getText());
		
		System.out.println("Customer Email: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Email']/parent::div//a")).getText());
		
		System.out.println("Customer Phone: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Customer Phone']/parent::div//lightning-formatted-text")).getText());
		
	    System.out.println("Deal Type: "+driver.findElement(By.xpath("//*[@id='brandBand_2']//div/p[@title='Deal Type']/parent::div//lightning-formatted-text")).getText());
		
	//	System.out.println("Marketing Source: "+driver.findElement(By.xpath("//*[@id='window']")).getText());
		
		
	    //Sales Details
		System.out.println("Deal "+driver.findElement(By.xpath("//flexipage-component2[1]//a[@title='Sales'][1]")).getText());
		
	}
	static void tearDown()
	{
		driver.close();
	}
}