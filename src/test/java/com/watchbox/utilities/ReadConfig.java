package com.watchbox.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig()
	{
		File src=new File("./Configuration/config.properties");
		
		try
			{
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
			}
		catch(Exception e)
			{
				System.out.println("Exception is"+ e.getMessage());
			}
	}
	public String getApplicationUrl()
	{
		String url=pro.getProperty("baseUrl");
		return url;
	}
	public String getChromepath()
	{
		String chromepath=pro.getProperty("chromePath");
		return chromepath;	
	}
	public String getWebDriver()
	{
		String webDriver=pro.getProperty("webDriver");
		return webDriver;	
	}
	
	public String getSFUrl()
	{
		String sfurl=pro.getProperty("sfcrmUrl");
		return sfurl;
	}
	
	public String getUsername()
	{
		String username=pro.getProperty("sfUsername");
		return username;
	}
	
	public String getPassword()
	{
		String password=pro.getProperty("sfPassword");
		return password;
	}
	
	public String getExpectedTitle()
	{
		String expectedTitle=pro.getProperty("expectedTitle");
		return expectedTitle;
	}
}
