import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver","E:\\Tejas\\eclipse-workspace\\WatchboxV1\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
    	
        String baseUrl = "https://dw-uat.thewatchbox.com/";
        driver.get(baseUrl);
        driver.manage().window().maximize();
     /*   if(driver.getTitle().equals(" Pre-Owned Luxury Watches Online | Buy, Sell, Trade Rolex Patek Panerai | TheWatchBox.com | Salesforce Commerce Cloud | 3.3.0"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		} */
		Thread.sleep(5000);
        driver.findElement(By.xpath("//a[text()='BRANDS' and @tabindex='3']")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//p[text()='Rolex']")).click();
        
	}

}

