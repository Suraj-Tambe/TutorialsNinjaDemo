package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
		prop=new Properties();
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		
		dataProp=new Properties();
		File dataFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
			
			try {
			FileInputStream dataFis=new FileInputStream(dataFile);
			dataProp.load(dataFis);
			}catch(Throwable e) {
				e.printStackTrace();
			}
			
			
			try {
			FileInputStream fis=new FileInputStream(propFile);
			prop.load(fis);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public WebDriver initializeBrowser(String browserName) {
		
		if(browserName.equals("chrome")) {
			ChromeOptions options=new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(options);
		}else if(browserName.equals("edge")) {
			EdgeOptions options=new EdgeOptions();
			options.addArguments("--remote-allow-origins=*");
			driver=new EdgeDriver(options);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicitWaitTime));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.pageLoadTime));
		driver.get(prop.getProperty("url"));
		return driver;
		
	}

}
