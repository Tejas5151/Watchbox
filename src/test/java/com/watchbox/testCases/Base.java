package com.watchbox.testCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.watchbox.utilities.ReadConfig;

public class Base {

	ReadConfig readconfig = new ReadConfig();
	public String baseUrl = readconfig.getApplicationUrl();
	public static WebDriver driver;
	public static Logger logger;
	String directoryPath = System.getProperty("user.dir");

	@BeforeClass
	public void setup() {
		// System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
		System.setProperty("webdriver.chrome.driver", directoryPath + "\\Drivers\\chromedriver.exe");
		// System.setProperty("webdriver.chrome.driver",
		// "E:\\Tejas\\eclipse-workspace\\WatchboxV1\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();

		logger = Logger.getLogger("WatchboxV1");
		PropertyConfigurator.configure("log4j.properties");
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}
}
