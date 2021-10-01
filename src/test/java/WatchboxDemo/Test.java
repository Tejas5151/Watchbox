package WatchboxDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.watchbox.utilities.ReadConfig;

public class Test {

	
	static WebDriver driver;
	WebDriverWait wait=new WebDriverWait(driver, 20);
	static ReadConfig readconfig=new ReadConfig();
	static String webDriver=readconfig.getWebDriver();
	static String chromepath=readconfig.getChromepath();
	static String baseUrl = readconfig.getApplicationUrl();
	
	public Test(WebDriver driver)
{
	this.driver = driver;
	}
	
		public static void main(String[] args) throws Exception {

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty(webDriver,chromepath);
			driver = new ChromeDriver(options);
				
			
			Test testObj=new Test(driver);
			testObj.setUp();
		//	testObj.navigateToWatchDetails();

	}
		public void setUp() throws Exception
		{

			driver.manage().window().maximize();     
			driver.get(baseUrl);
			implicitWait();
			
		driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div[3]/div/div[1]/div/div/nav/div/div[1]/div[1]/ul/li[1]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("country")).sendKeys("United States");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@value='Confirm']")).click();
			
			
			implicitWait();
		//	String ActualTitle = driver.getTitle();
	//		Assert.assertEquals(ExpectedTitle, ActualTitle);
		
		}
		public void navigateToWatchDetails() throws InterruptedException
			{
			
	   		Boolean isText=driver.findElement(By.xpath("//div[text()='Continue to the United States site']")).isDisplayed();
	   		if(isText==true)
	   	    {
	   			driver.findElement(By.xpath("//div[text()='Continue to the United States site']")).click();
	    		 }

	   		
		//	WebElement cookieButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='affirm consent-btn close-button']")));
			//cookieButton.click();
			
	   	//	WebElement watch=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='https://watchbox-cdn.imgix.net/posted-product-images/637666106718156028.jpg?w=1184&h=1184']")));
	   	//	watch.click();

	   		implicitWait();
	   	//	WebElement mknofferButton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='make-an-offers']")));
	   		//mknofferButton.click();  	
	   		

	   		List<WebElement> allWatches = driver.findElements(By.xpath("//div[@class='grid-container']/div"));
	   			  int i=0; 		
			for( WebElement watch1 : allWatches){
						i++;		
				if(watch1.getText().contains("Coming Soon") && watch1.getText().contains("$") )
				{
					continue;
					
				}
				else if(watch1.getText().contains("$"))
				{
				System.out.println(watch1.getText());
				Thread.sleep(3000);
				System.out.println("Value of I:"+i);
				WebElement w=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='grid-container']/div["+i+"]//ancestor::div[@class='img-placement wm']")));
				w.click();
			break;
				}
				else{continue;}
				}

			//			if(str!=" ")
			//		{
			//	watch1.click();
			//	break;
				//	}
		//	}*/
	   	//	String[] strArray = new String[100];
		//	Iterator<WebElement> itr=allWatches.iterator();
		//	while(itr.hasNext()) {
			//	System.out.println("**"+itr);
				
				//int i=0;
				//strArray[i]=itr.next().getText();
				//i++;
				
			
		//	System.out.println("*****"+strArray);
		/*	Boolean isText=driver.findElement(By.xpath("//div[text()='Continue to the United States site']")).isDisplayed();
	   		if(isText==true)
	   	    {
	   			driver.findElement(By.xpath("//div[text()='Continue to the United States site']")).click();
	    		 }*/

			}
		public void implicitWait()
		{
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
}
